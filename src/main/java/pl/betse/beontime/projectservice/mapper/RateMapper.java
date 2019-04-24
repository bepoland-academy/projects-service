package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.betse.beontime.projectservice.bo.RateBo;
import pl.betse.beontime.projectservice.entity.ProjectAssignmentsEntity;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;
import pl.betse.beontime.projectservice.model.RateBody;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, ProjectMapper.class})
public abstract class RateMapper {

    @Mapping(source = "projectRoleEntity.roleGuid", target = "roleId")
    @Mapping(source = "projectAssignmentsEntity", target = "consultants")
    public abstract RateBo fromEntityToBo(ProjectRateEntity projectRateEntity);


    @Mapping(source = "roleId", target = "projectRoleEntity.roleGuid")
    @Mapping(source = "consultants", target = "projectAssignmentsEntity")
    public abstract ProjectRateEntity fromBoToEntiy(RateBo rateBo);

    @IterableMapping(elementTargetType = String.class, qualifiedByName = "mapConsultant")
    protected abstract List<String> mapConsultants(List<ProjectAssignmentsEntity> roles);

    @Named("mapConsultant")
    String mapConsultant(ProjectAssignmentsEntity projectAssignmentsEntity) {
        return projectAssignmentsEntity.getUserGuid();
    }

    @IterableMapping(elementTargetType = ProjectAssignmentsEntity.class, qualifiedByName = "mapConsultantReverse")
    protected abstract List<ProjectAssignmentsEntity> mapConsultantsReverse(List<String> roles);

    @Named("mapConsultantReverse")
    ProjectAssignmentsEntity mapConsultantReverse(String item) {
        ProjectAssignmentsEntity projectAssignmentsEntity = new ProjectAssignmentsEntity();
        projectAssignmentsEntity.setUserGuid(item);
        return projectAssignmentsEntity;
    }

    public abstract RateBody fromBoToBody(RateBo rateBo);

    public abstract RateBo fromBodyToBo(RateBody rateBody);
}
