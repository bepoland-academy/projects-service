package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.betse.beontime.projectservice.bo.RoleBo;
import pl.betse.beontime.projectservice.entity.ProjectRateEntity;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;
import pl.betse.beontime.projectservice.model.RoleBody;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GuidMapper.class})
public abstract class RoleMapper {

    @Mapping(source = "roleGuid", target = "roleId")
//    @Mapping(source = "projectRateEntities", target = "projects", qualifiedByName = "hasAnyRates")
    public abstract RoleBo mapEntityToBo(ProjectRoleEntity projectRoleEntity);

    @Mapping(source = "roleId", target = "roleGuid", qualifiedByName = "mapGuid")
    public abstract ProjectRoleEntity mapRoleBoToRoleEntity(RoleBo roleBo);

    public abstract RoleBody mapRoleBoToRoleBody(RoleBo roleBo);

    public abstract RoleBo mapRoleBodyToRoleBo(RoleBody roleBody);

    @Named("hasAnyRates")
    public boolean hasAnyRates(List<ProjectRateEntity> projectRateEntities){
        return !projectRateEntities.isEmpty();
    }
}
