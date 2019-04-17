package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.*;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.bo.RateBo;
import pl.betse.beontime.projectservice.entity.ProjectAssignmentsEntity;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;
import pl.betse.beontime.projectservice.model.ProjectBody;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {GuidMapper.class, ClientMapper.class})
public abstract class ProjectMapper {

    @Mappings({
            @Mapping(source = "projectRoleEntity.roleGuid",target = "roleId"),
            @Mapping(source = "projectAssignmentsEntity",target = "consultants", qualifiedByName = "mapTest")
    })
    public abstract RateBo fromRateEntityToBo (ProjectRateEntity projectRateEntity);

    @IterableMapping(elementTargetType = String.class, qualifiedByName = "mapTest")
    protected abstract List<String> mapRolesFromEntity(List<ProjectAssignmentsEntity> roles);

    @Named("mapTest")
    String mapRoleEntityToString(ProjectAssignmentsEntity projectAssignmentsEntity) {
        return projectAssignmentsEntity.getUserGuid();
    }

    @Mappings({
            @Mapping(source = "guid", target = "id"),
            @Mapping(source = "clientEntity.guid", target = "clientGuid"),
    })
    public abstract ProjectBo fromEntityToBo(ProjectEntity projectEntity);

//    @Mapping(source = "guid", target = "id")
//    @Mapping(source = "clientEntity", target = "clientBo")
//    @Mapping(source = "departmentGuid", target = "departmentGuid")
//    ProjectBo mapProjectEntityToProjectBo(ProjectEntity projectEntity);

//    public ProjectBo fromEntityToBo(ProjectEntity projectEntity) {
//        return ProjectBo.builder()
//                .id(projectEntity.getGuid())
//                .name(projectEntity.getName())
//                .comments(projectEntity.getComments())
//                .clientGuid(projectEntity.getClientEntity().getGuid())
//                .departmentGuid(projectEntity.getDepartmentGuid())
//                .active(projectEntity.getActive())
//                .OffSiteOnly(projectEntity.getOffSiteOnly())
//                .rates(projectEntity.getProjectRateEntities().stream().map(this::mapRateEntityToRateBo).collect(Collectors.toList()))
//                .build();
//    }


    public ProjectBo map(ProjectEntity entity){
        ProjectBo bo = new ProjectBo();
        bo.setId(entity.getGuid());

        List<RateBo> rates = new ArrayList<>();
        entity.getRates().stream().forEach(rate -> rates.add(mapRate(rate)));
        bo.setRates(rates);

        return bo;
    }

    private RateBo mapRate(ProjectRateEntity entity){
        RateBo bo = new RateBo();

        List<String> consultants = new ArrayList<>();
        entity.getProjectAssignmentsEntity().stream().forEach( assignment -> {
                consultants.add(assignment.getUserGuid());
            });

        bo.setConsultants(consultants);
        return bo;
    }

    @Mapping(source = "id", target = "guid", qualifiedByName = "mapGuid")
    @Mapping(source = "clientGuid", target = "clientEntity.guid")
    public abstract ProjectEntity mapProjectBoToProjectEntity(ProjectBo projectBo);

    @Mapping(source = "id", target = "projectId")
    public abstract ProjectBody mapProjectBoToProjectBody(ProjectBo projectBo);

    @Mapping(source = "projectId", target = "id")
    public abstract ProjectBo mapProjectBodyToProjectBo(ProjectBody projectBody);

}
