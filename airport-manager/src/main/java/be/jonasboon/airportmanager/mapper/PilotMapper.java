package be.jonasboon.airportmanager.mapper;

import be.jonasboon.airportmanager.dto.PilotDTO;
import be.jonasboon.airportmanager.model.Pilot;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PilotMapper {

    public static PilotDTO toDto(Pilot pilot){
        return PilotDTO.builder()
                .withId(pilot.getId())
                .withFirstName(pilot.getFirstName())
                .withLastName(pilot.getLastName())
                .build();
    }

    public static Pilot toEntity(PilotDTO pilotDTO){
        return Pilot.builder()
                .firstName(pilotDTO.getFirstName())
                .lastName(pilotDTO.getLastName())
                .build();
    }

    public static Pilot toEntity(Integer id, PilotDTO pilotDTO){
        return Pilot.builder()
                .id(id)
                .firstName(pilotDTO.getFirstName())
                .lastName(pilotDTO.getLastName())
                .build();
    }

}
