package pl.betse.beontime.projectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.betse.beontime.projectservice.bo.ClientBo;
import pl.betse.beontime.projectservice.entity.ClientEntity;
import pl.betse.beontime.projectservice.model.ClientBody;

@Mapper(componentModel = "spring", uses = {GuidMapper.class})
public interface ClientMapper {

    @Mapping(source = "guid", target = "clientId")
    ClientBo mapClientEntityToClientBo(ClientEntity clientEntity);

    @Mapping(source = "clientId", target = "guid", qualifiedByName = "mapGuid")
    ClientEntity mapClientBoToClientEntity(ClientBo clientBo);

    @Mapping(source = "clientId", target = "clientId")
    ClientBody mapClientBoToClientBody(ClientBo clientBo);

    @Mapping(source = "clientId", target = "clientId")
    ClientBo mapClientBodyToClientBo(ClientBody clientBody);

}