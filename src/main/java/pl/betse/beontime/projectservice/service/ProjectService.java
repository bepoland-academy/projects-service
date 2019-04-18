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
        projectRepository.save(projectEntity);
        return projectMapper.fromEntityToBo(projectEntity);
    }

    public ProjectBo updateProject(String guid, ProjectBo projectBo) {
        ProjectEntity projectEntity = projectRepository.findByGuid(guid)
                .orElseThrow(ProjectNotFoundException::new);
        updateProjectFields(projectBo, projectEntity);
        projectRepository.save(projectEntity);
        return projectMapper.fromEntityToBo(projectEntity);
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

    private void updateProjectFields(ProjectBo projectBo, ProjectEntity projectEntity) {
        ClientEntity clientEntity = clientRepository.findByGuid(projectBo.getClientGuid()).orElseThrow(ClientNotFoundException::new);
        projectEntity.setClientEntity(clientEntity);
        projectEntity.setName(projectBo.getName() == null ? projectEntity.getName() : projectBo.getName());
        projectEntity.setComments(projectBo.getComments() == null ? projectEntity.getComments() : projectBo.getComments());
        projectEntity.setActive(projectBo.getActive());
        projectEntity.setDepartmentGuid(projectBo.getDepartmentGuid() == null ? projectEntity.getDepartmentGuid() : projectBo.getDepartmentGuid());
        projectEntity.setOffSiteOnly(projectBo.getOffSiteOnly());

        if (!projectBo.getRates().isEmpty()) {


            for (RateBo rate : projectBo.getRates()) {

                ProjectRoleEntity projectRoleEntity = roleRepository.getOne(rate.getRoleId());

                ProjectRateEntity projectRateEntity = rateRepository.findByProjectRoleEntityAndProjectEntity_Guid(projectRoleEntity, projectEntity.getGuid());

                if (projectRateEntity == null) {
                    projectRateEntity = new ProjectRateEntity();
                }

                projectRateEntity.setRate(rate.getRate());
                if (projectEntity.getOffSiteOnly()) {
                    projectRateEntity.setOnSiteRate(BigDecimal.ZERO);
                } else {
                    projectRateEntity.setOnSiteRate(rate.getOnSiteRate());
                }

                List<ProjectAssignmentsEntity> projectAssignmentsEntityList = new ArrayList<>();

                projectRateEntity.setProjectAssignmentsEntity(projectAssignmentsEntityList);

                projectRateEntity.setProjectEntity(projectEntity);

                projectRateEntity.setProjectRoleEntity(projectRoleEntity);

                rateRepository.save(projectRateEntity);

                roleRepository.save(projectRoleEntity);

                for (String consultantGuid : rate.getConsultants()) {

                    if (!assignmentRepository.existsByUserGuidAndProjectRateEntity(consultantGuid, projectRateEntity)) {
                        ProjectAssignmentsEntity projectAssignmentsEntity = new ProjectAssignmentsEntity();
                        projectAssignmentsEntity.setUserGuid(consultantGuid);
                        projectAssignmentsEntity.setProjectRateEntity(projectRateEntity);
                        projectAssignmentsEntityList.add(projectAssignmentsEntity);
                        assignmentRepository.save(projectAssignmentsEntity);
                    }


                }




            }


        }

    }

    public boolean checkIfRateExists(String projectGuid) {
        return projectRepository.existsByRates(projectGuid);
    }


}
