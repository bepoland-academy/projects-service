package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.betse.beontime.projectservice.bo.ClientBo;
import pl.betse.beontime.projectservice.entity.ClientEntity;
import pl.betse.beontime.projectservice.model.ClientBody;

@Mapper(componentModel = "spring", uses = GuidMapper.class)
public abstract class ClientMapper {

    @Mappings(
            @Mapping(source = "guid", target = "id")
    )
    public abstract ClientBo mapClientEntityToClientBo(ClientEntity clientEntity);

    @Mappings(
            @Mapping(source = "id", target = "guid", qualifiedByName = "mapGuid")
    )
    public abstract ClientEntity mapClientBoToClientEntity(ClientBo clientBo);

    @Mappings(
            @Mapping(source = "id", target = "clientId")
    )
    public abstract ClientBody mapClientBoToClientBody(ClientBo clientBo);

    @Mappings(
            @Mapping(source = "clientId", target = "id")
    )
    public abstract ClientBo mapClientBodyToClientBo(ClientBody clientBody);

}
