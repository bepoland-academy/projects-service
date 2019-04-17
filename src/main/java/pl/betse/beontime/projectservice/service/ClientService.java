package pl.betse.beontime.projectservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.betse.beontime.projectservice.bo.ClientBo;
import pl.betse.beontime.projectservice.entity.ClientEntity;
import pl.betse.beontime.projectservice.exception.ClientAlreadyExistException;
import pl.betse.beontime.projectservice.exception.ClientAssignedToProjectException;
import pl.betse.beontime.projectservice.exception.ClientNotFoundException;
import pl.betse.beontime.projectservice.mapper.ClientMapper;
import pl.betse.beontime.projectservice.repository.ClientRepository;
import pl.betse.beontime.projectservice.repository.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ProjectRepository projectRepository;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, ProjectRepository projectRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.projectRepository = projectRepository;
    }

    public List<ClientBo> allClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::mapClientEntityToClientBo)
                .collect(Collectors.toList());
    }

    public ClientBo getByGuid(String guid) {
        ClientEntity clientEntity = clientRepository
                .findByGuid(guid)
                .orElseThrow(ClientNotFoundException::new);
        return clientMapper.mapClientEntityToClientBo(clientEntity);
    }

    public ClientBo addNewClient(ClientBo clientBo) {
        if (clientRepository.findByName(clientBo.getName()).isPresent()) {
            throw new ClientAlreadyExistException();
        }
        ClientEntity clientEntity = clientRepository
                .save(clientMapper.mapClientBoToClientEntity(clientBo));
        return clientMapper.mapClientEntityToClientBo(clientEntity);
    }

    public ClientBo updateClient(String guid, ClientBo clientBo) {
        ClientEntity clientEntity = clientRepository
                .findByGuid(guid)
                .orElseThrow(ClientNotFoundException::new);
        clientEntity.setName(clientBo.getName() == null ? clientEntity.getName() : clientBo.getName());
        clientRepository.save(clientEntity);
        return clientMapper.mapClientEntityToClientBo(clientEntity);
    }

    public void deleteClient(String guid) {
        ClientEntity clientEntity = clientRepository
                .findByGuid(guid)
                .orElseThrow(ClientNotFoundException::new);
        if (projectRepository.existsByClientEntity(clientEntity)) {
            log.error("Client cannot be deleted because of assignment to project.");
            throw new ClientAssignedToProjectException();
        }
        clientRepository.delete(clientEntity);
    }

//    public boolean checkIfProjectHasAnyClients(String guid){
//        ClientEntity clientEntity = clientRepository.findByGuid(guid).
//                orElseThrow(ClientNotFoundException::new);
//        return projectRepository.existsByClientEntity(clientEntity);
//    }

}
