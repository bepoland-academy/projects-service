package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.betse.beontime.projectservice.bo.RolesBo;
import pl.betse.beontime.projectservice.entity.ProjectRolesEntity;
import pl.betse.beontime.projectservice.model.RoleBody;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "roleGuid", target = "roleId")
    RolesBo mapRoleEntityToRoleBo(ProjectRolesEntity projectRolesEntity);

    @Mapping(source = "roleId", target = "roleGuid")
    ProjectRolesEntity mapRoleBoToRoleEntity(RolesBo rolesBo);

    RoleBody mapRoleBoToRoleBody (RolesBo rolesBo);

    RolesBo mapRoleBodyToRoleBo (RoleBody roleBody);
}
