package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.betse.beontime.projectservice.bo.RoleBo;
import pl.betse.beontime.projectservice.entity.ProjectRoleEntity;
import pl.betse.beontime.projectservice.model.RoleBody;

@Mapper(componentModel = "spring", uses = {GuidMapper.class})
public interface RoleMapper {

    @Mapping(source = "roleGuid", target = "roleId" )
    RoleBo mapRoleEntityToRoleBo(ProjectRoleEntity projectRoleEntity);

    @Mapping(source = "roleId", target = "roleGuid", qualifiedByName = "mapGuid")
    ProjectRoleEntity mapRoleBoToRoleEntity(RoleBo roleBo);

    RoleBody mapRoleBoToRoleBody (RoleBo roleBo);

    RoleBo mapRoleBodyToRoleBo (RoleBody roleBody);
}
