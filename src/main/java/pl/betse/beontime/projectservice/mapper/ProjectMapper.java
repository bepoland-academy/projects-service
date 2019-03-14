package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.entity.ProjectEntity;
import pl.betse.beontime.projectservice.model.ProjectBody;

@Mapper(componentModel = "spring", uses = {GuidMapper.class, ClientMapper.class})
public interface ProjectMapper {

    @Mappings({
            @Mapping(source = "guid", target = "id"),
            @Mapping(source = "clientEntity", target = "clientBo"),
            @Mapping(source = "departmentGuid", target = "departmentGuid")
    })
    ProjectBo mapProjectEntityToProjectBo(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(source = "id", target = "guid", qualifiedByName = "mapGuid"),
            @Mapping(source = "clientBo", target = "clientEntity"),
            @Mapping(source = "departmentGuid", target = "departmentGuid")
    }
    )
    ProjectEntity mapProjectBoToProjectEntity(ProjectBo projectBo);

    @Mappings({
            @Mapping(source = "id", target = "projectId"),
            @Mapping(source = "clientBo", target = "client"),
            @Mapping(source = "departmentGuid", target = "department")
    })
    ProjectBody mapProjectBoToProjectBody(ProjectBo projectBo);

    @Mappings({
            @Mapping(source = "projectId", target = "id"),
            @Mapping(source = "client", target = "clientBo"),
            @Mapping(source = "department", target = "departmentGuid")
    }
    )
    ProjectBo mapProjectBodyToProjectBo(ProjectBody projectBody);

}
