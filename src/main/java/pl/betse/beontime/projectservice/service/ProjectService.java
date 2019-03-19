package pl.betse.beontime.projectservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.entity.ClientEntity;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.exception.ClientNotFoundException;
import pl.betse.beontime.projectservice.exception.ProjectAlreadyExistException;
import pl.betse.beontime.projectservice.exception.ProjectNotFoundException;
import pl.betse.beontime.projectservice.mapper.ProjectMapper;
import pl.betse.beontime.projectservice.repository.ClientRepository;
import pl.betse.beontime.projectservice.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectBo> allProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::mapProjectEntityToProjectBo)
                .collect(Collectors.toList());
    }

    public ProjectBo getProjectByGuid(String guid) {
        ProjectEntity projectEntity = projectRepository
                .findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);
        return projectMapper.mapProjectEntityToProjectBo(projectEntity);
    }

    public List<ProjectBo> getProjectsByDepartmentId(String departmentId) {
        List<ProjectEntity> projectEntities = projectRepository
                .findProjectByDepartmentGuid(departmentId)
                .orElse(new ArrayList<>());
        return projectEntities.stream()
                .map(projectMapper::mapProjectEntityToProjectBo)
                .collect(Collectors.toList());
    }

    public ProjectBo addNewProject(ProjectBo projectBo) {
        if (projectRepository.findByGuid(projectBo.getId()).isPresent()) {
            throw new ProjectAlreadyExistException();
        }
        ProjectEntity projectEntity = projectMapper.mapProjectBoToProjectEntity(projectBo);
        ClientEntity clientEntity = clientRepository.findByGuid(projectBo.getClientBo().getClientId())
                .orElseThrow(ClientNotFoundException::new);
        projectEntity.setClientEntity(clientEntity);
        projectRepository.save(projectEntity);
        return projectMapper.mapProjectEntityToProjectBo(projectEntity);
    }

    public ProjectBo updateProject(String guid, ProjectBo projectBo) {
        ProjectEntity projectEntity = projectRepository.findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);
        updateProjectFields(projectBo, projectEntity);
        projectRepository.save(projectEntity);
        return projectMapper.mapProjectEntityToProjectBo(projectEntity);
    }

    public void deleteProjectByGuid(String guid) {
        ProjectEntity projectEntity = projectRepository.findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);
        projectRepository.delete(projectEntity);
    }

    private void updateProjectFields(ProjectBo projectBo, ProjectEntity projectEntity) {
        ClientEntity clientEntity = clientRepository.findByGuid(projectBo.getClientBo().getClientId()).orElseThrow(ClientNotFoundException::new);
        projectEntity.setClientEntity(clientEntity);
        projectEntity.setName(projectBo.getName() == null ? projectEntity.getName() : projectBo.getName());
        projectEntity.setComments(projectBo.getComments() == null ? projectEntity.getComments() : projectBo.getComments());
        projectEntity.setRate(projectBo.getRate() == null ? projectEntity.getRate() : projectBo.getRate());
        projectEntity.setActive(projectBo.isActive());
    }
}
