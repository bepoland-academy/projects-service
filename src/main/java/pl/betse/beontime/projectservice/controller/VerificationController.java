package pl.betse.beontime.projectservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.betse.beontime.projectservice.service.ClientService;

@Slf4j
@RestController
@RequestMapping("/project")
public class VerificationController {

    private final ClientService clientService;

    public VerificationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clientExist")
    public boolean checkIfClientExistsInProject(@RequestParam("guid") String clientGuid){
        return clientService.checkIfProjectHasAnyClients(clientGuid);
    }
}
