package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.UptimeDTO;
import eu.getmangos.entities.Uptime;

@Mapper(componentModel = "cdi")
public interface UptimeMapper {

    @Mapping(source = "u.id.realmId", target = "realmId")
    @Mapping(source = "id.started", target = "started")
    UptimeDTO uptimeToDTO(Uptime u);

    @InheritInverseConfiguration
    Uptime dtoToEntity(UptimeDTO u);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }
}
