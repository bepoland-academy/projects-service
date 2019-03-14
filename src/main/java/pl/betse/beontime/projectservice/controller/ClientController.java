package pl.betse.beontime.projectservice.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.betse.beontime.projectservice.bo.ClientBo;
import pl.betse.beontime.projectservice.mapper.ClientMapper;
import pl.betse.beontime.projectservice.model.ClientBody;
import pl.betse.beontime.projectservice.service.ClientService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Resources<ClientBody>> getListOfAllClients() {
        List<ClientBody> clients = clientService.allClients().stream()
                .map(clientMapper::mapClientBoToClientBody)
                .collect(Collectors.toList());
        clients.forEach(this::addLinks);
        return ResponseEntity.ok(new Resources<>(clients));
    }

    @GetMapping("/{guid}")
    public ResponseEntity getClientByGuid(@PathVariable String guid) {
        ClientBo clientBo = clientService.getByGuid(guid);
        ClientBody clientBody = clientMapper.mapClientBoToClientBody(clientBo);
        addLinks(clientBody);
        return ResponseEntity.ok(new Resource<>(clientBody));
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody ClientBody clientBody) {
        ClientBo clientBo = clientService.addNewClient(clientMapper.mapClientBodyToClientBo(clientBody));
        URI location = linkTo(methodOn(ClientController.class).getClientByGuid(clientBo.getClientId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{guid}")
    public ResponseEntity updateClient(@PathVariable("guid") String guid, @RequestBody ClientBody clientBody) {
        clientMapper.mapClientBoToClientBody(clientService.updateClient(guid, clientMapper.mapClientBodyToClientBo(clientBody)));
        return ResponseEntity.ok().build();
    }


    private void addLinks(ClientBody clientBody) {
        clientBody.add(linkTo(methodOn(ClientController.class).getClientByGuid(clientBody.getClientId())).withSelfRel());
    }


}
