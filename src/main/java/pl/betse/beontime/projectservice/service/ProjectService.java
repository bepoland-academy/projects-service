package pl.betse.beontime.projectservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.bo.RateBo;
import pl.betse.beontime.projectservice.entity.*;
import pl.betse.beontime.projectservice.exception.*;
import pl.betse.beontime.projectservice.mapper.ProjectMapper;
import pl.betse.beontime.projectservice.mapper.RateMapper;
import pl.betse.beontime.projectservice.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final ProjectMapper projectMapper;
    private final RateMapper rateMapper;
    private final RateRepository rateRepository;
    private final RoleRepository roleRepository;
    private final AssignmentRepository assignmentRepository;

    @Value("${api-project-exist}")
    private String API_PROJECT_EXIST;

    public ProjectService(ProjectRepository projectRepository,
                          ClientRepository clientRepository,
                          ProjectMapper projectMapper,
                          RateMapper rateMapper,
                          RateRepository rateRepository,
                          RoleRepository roleRepository,
                          AssignmentRepository assignmentRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
        this.projectMapper = projectMapper;
        this.rateMapper = rateMapper;
        this.rateRepository = rateRepository;
        this.roleRepository = roleRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public List<ProjectBo> allProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::fromEntityToBo)
                .collect(Collectors.toList());
    }

    public ProjectBo getProjectByGuid(String guid) {
        ProjectEntity projectEntity = projectRepository
                .findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);
        return projectMapper.fromEntityToBo(projectEntity);
    }

    public List<ProjectBo> findByClient(String clientGuid) {
        ClientEntity clientEntity = clientRepository.findByGuid(clientGuid).orElseThrow(ClientNotFoundException::new);
        return projectRepository.findByClientEntity(clientEntity).stream()
                .map(projectMapper::fromEntityToBo)
                .collect(Collectors.toList());
    }

    public List<ProjectBo> getProjectsByDepartmentId(String departmentId) {
        List<ProjectEntity> projectEntities = projectRepository
                .findProjectByDepartmentGuid(departmentId)
                .orElse(new ArrayList<>());
        return projectEntities.stream()
                .map(projectMapper::fromEntityToBo)
                .collect(Collectors.toList());
    }

    public ProjectBo addNewProject(ProjectBo projectBo) {
        if (projectRepository.findByGuid(projectBo.getProjectId()).isPresent()) {
            throw new ProjectAlreadyExistException();
        }
        ProjectEntity projectEntity = projectMapper.fromBoToEntity(projectBo);

        ClientEntity clientEntity = clientRepository.findByGuid(projectBo.getClientGuid())
                .orElseThrow(ClientNotFoundException::new);
        projectEntity.setClientEntity(clientEntity);
        ProjectEntity createdProjectEntity = projectRepository.save(projectEntity);

        createdProjectEntity
                .getRates()
                .stream()
                .forEach(rateEntity -> {
                    rateEntity.setProjectEntity(createdProjectEntity);
                    ProjectRoleEntity projectRoleEntity =
                            roleRepository.findByRoleGuid(rateEntity.getProjectRoleEntity().getRoleGuid()).get();
                    rateEntity.setProjectRoleEntity(projectRoleEntity);

                    ProjectRateEntity createdProjectRateEntity = rateRepository.save(rateEntity);

                    createdProjectRateEntity.getProjectAssignmentsEntity().stream().forEach(
                            assignment -> {
                                assignment.setProjectRateEntity(createdProjectRateEntity);
                                assignmentRepository.save(assignment);
                            }
                    );
                });

        return projectMapper.fromEntityToBo(projectEntity);

    }

    public ProjectBo updateProject(String guid, ProjectBo projectBo) {
        ProjectEntity projectEntity = projectRepository.findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);

        ClientEntity clientEntity = clientRepository.findByGuid(projectBo.getClientGuid())
                .orElseThrow(ClientNotFoundException::new);
        projectEntity.setClientEntity(clientEntity);

        for (ProjectRateEntity projectRateEntity : projectEntity.getRates()) {

            for (ProjectAssignmentsEntity assignmentsEntity : projectRateEntity.getProjectAssignmentsEntity()) {
                assignmentRepository.delete(assignmentsEntity);
            }
            rateRepository.delete(projectRateEntity);
        }

        ////
        ProjectEntity projectToBeUpdatedEntity = projectMapper.fromBoToEntity(projectBo);
        projectToBeUpdatedEntity.setId(projectEntity.getId());
        projectToBeUpdatedEntity
                .getRates()
                .stream()
                .forEach(rateEntity -> {
                    rateEntity.setProjectEntity(projectEntity);
                    ProjectRoleEntity projectRoleEntity =
                            roleRepository.findByRoleGuid(rateEntity.getProjectRoleEntity().getRoleGuid()).get();
                    rateEntity.setProjectRoleEntity(projectRoleEntity);

                    ProjectRateEntity createdProjectRateEntity = rateRepository.save(rateEntity);

                    createdProjectRateEntity.getProjectAssignmentsEntity().stream().forEach(
                            assignment -> {
                                assignment.setProjectRateEntity(createdProjectRateEntity);
                                assignmentRepository.save(assignment);
                            }
                    );
                });

        ////

        projectEntity.setComments(projectToBeUpdatedEntity.getComments());
        projectEntity.setName(projectToBeUpdatedEntity.getName());
        projectEntity.setActive(projectToBeUpdatedEntity.getActive());
        projectEntity.setRates(null);

        projectRepository.save(projectEntity);
        return projectMapper.fromEntityToBo(projectToBeUpdatedEntity);
    }

    public void deleteProjectByGuid(String guid) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_PROJECT_EXIST + guid;
        boolean isTimeEntryForProjectExist = restTemplate.getForEntity(url, Boolean.class).getBody().booleanValue();
        if (!isTimeEntryForProjectExist) {
            ProjectEntity projectEntity = projectRepository.findByGuid(guid)
                    .orElseThrow(ProjectNotFoundException::new);
            if (projectEntity.getActive()) {
                log.error("Project cannot be deleted while is active");
                throw new ProjectIsActiveException();
            }
            projectRepository.delete(projectEntity);
        } else {
            log.error("Project with guid =" + guid + " cannot be deleted because time entries exist.");
            throw new TimeEntriesForProjectExists();
        }

    }
}