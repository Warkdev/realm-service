package eu.getmangos.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.LinksDTO;
import eu.getmangos.entities.RealmCharacters;

@Mapper(componentModel = "cdi")
public interface LinksMapper {

    @Mapping(source = "link.id.realmID", target = "realmID")
    @Mapping(source = "link.id.accountID", target = "accountID")
    LinksDTO linkToDTO(RealmCharacters link);

    @InheritInverseConfiguration
    RealmCharacters dtoToEntity(LinksDTO link);
}
