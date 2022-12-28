package be.jonasboon.airportmanager.mapper;

import be.jonasboon.airportmanager.dto.FlightDTO;
import be.jonasboon.airportmanager.model.Flight;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlightMapper {

    public static FlightDTO toDto(Flight flight){
        return FlightDTO.builder()
                .withId(flight.getId())
                .withPilotDTO(PilotMapper.toDto(flight.getPilot()))
                .build();
    }
}
