package pl.betse.beontime.projectservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.betse.beontime.projectservice.bo.ClientBo;
import pl.betse.beontime.projectservice.mapper.ClientMapper;
import pl.betse.beontime.projectservice.model.ClientBody;
import pl.betse.beontime.projectservice.service.ClientService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @Value("${api-prefix}")
    private String API_PREFIX;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Resources<ClientBody>> getListOfAllClients() {
        List<ClientBody> clients = clientService.allClients().stream()
                .map(clientMapper::fromBoToBody)
                .collect(Collectors.toList());
        clients.forEach(this::addLinks);
        return ResponseEntity.ok(new Resources<>(clients));
    }

    @GetMapping("/{guid}")
    public ResponseEntity getClientByGuid(@PathVariable String guid) {
        ClientBo clientBo = clientService.getByGuid(guid);
        ClientBody clientBody = clientMapper.fromBoToBody(clientBo);
        addLinks(clientBody);
        return ResponseEntity.ok(new Resource<>(clientBody));
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody @Valid ClientBody clientBody) throws URISyntaxException {
        ClientBo clientBo = clientService.addNewClient(clientMapper.fromBodyToBo(clientBody));
        URI location = linkTo(methodOn(ClientController.class).getClientByGuid(clientBo.getClientId())).toUri();
        return ResponseEntity.created(new URI(API_PREFIX + location.getPath())).build();
    }

    @PutMapping("/{guid}")
    public ResponseEntity updateClient(@PathVariable("guid") String guid, @RequestBody ClientBody clientBody) {
        clientMapper.fromBoToBody(clientService.updateClient(guid, clientMapper.fromBodyToBo(clientBody)));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity deleteClient(@PathVariable("guid") String guid) {
        clientService.deleteClient(guid);
        return ResponseEntity.ok().build();
    }

    private void addLinks(ClientBody clientBody) {
        clientBody.add(linkTo(methodOn(ClientController.class).getClientByGuid(clientBody.getClientId())).withSelfRel());
        if (!clientBody.isProject()) {
            clientBody.add(linkTo(methodOn(ClientController.class).deleteClient(clientBody.getClientId())).withRel("DELETE"));
        }
    }

}
