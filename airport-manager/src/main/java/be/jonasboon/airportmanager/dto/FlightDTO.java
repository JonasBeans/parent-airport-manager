package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class FlightDTO {

    @JsonProperty("id")
    String id;

    @JsonProperty("pilot")
    PilotDTO pilotDTO;

    // TODO: 20/11/2022 join aircraft 
}
