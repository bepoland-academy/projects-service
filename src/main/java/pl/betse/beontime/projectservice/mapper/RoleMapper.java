package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import pl.betse.beontime.projectservice.bo.RolesBo;
import pl.betse.beontime.projectservice.entity.ProjectRolesEntity;
import pl.betse.beontime.projectservice.model.RoleBody;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RolesBo mapRoleEntityToRoleBo(ProjectRolesEntity projectRolesEntity);

    ProjectRolesEntity mapRoleBoToRoleEntity(RolesBo rolesBo);

    RoleBody mapRoleBoToRoleBody (RolesBo rolesBo);

    RolesBo mapRoleBodyToRoleBo (RoleBody roleBody);
}
