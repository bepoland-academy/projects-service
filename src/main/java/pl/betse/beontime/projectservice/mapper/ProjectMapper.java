package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.model.ProjectBody;

@Mapper(componentModel = "spring", uses = GuidMapper.class)
public interface ProjectMapper {

    @Mappings(
            @Mapping(source = "guid", target = "id")
    )
    ProjectBo mapProjectEntityToProjectBo(ProjectEntity projectEntity);

    @Mappings(
            @Mapping(source = "id", target = "guid", qualifiedByName = "mapGuid")
    )
    ProjectEntity mapProjectBoToProjectEntity(ProjectBo projectBo);

    @Mappings(
            @Mapping(source = "id", target = "projectId")
    )
    ProjectBody mapProjectBoToProjectBody(ProjectBo projectBo);

    @Mappings(
            @Mapping(source = "projectId", target = "id")
    )
    ProjectBo mapProjectBodyToProjectBo(ProjectBody projectBody);

}
