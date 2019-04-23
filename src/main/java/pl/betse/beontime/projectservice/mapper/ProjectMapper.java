package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.model.ProjectBody;

@Mapper(componentModel = "spring", uses = {GuidMapper.class, RateMapper.class, RoleMapper.class})
public abstract class ProjectMapper {

    @Mapping(source = "projectId", target = "guid", qualifiedByName = "guidMapper")
    @Mapping(source = "clientGuid", target = "clientEntity.guid")
    public abstract ProjectEntity fromBoToEntity(ProjectBo projectBo);

    @Mapping(source = "guid", target = "projectId")
    @Mapping(source = "clientEntity.guid", target = "clientGuid")
    public abstract ProjectBo fromEntityToBo(ProjectEntity projectEntity);

    @Mapping(source = "projectId", target = "projectId")
    public abstract ProjectBody mapProjectBoToProjectBody(ProjectBo projectBo);

    @Mapping(source = "projectId", target = "projectId")
    public abstract ProjectBo mapProjectBodyToProjectBo(ProjectBody projectBody);

}
