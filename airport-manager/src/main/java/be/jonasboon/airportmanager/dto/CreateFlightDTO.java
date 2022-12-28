package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class CreateFlightDTO {

    @JsonProperty("id")
    String id;

    @JsonProperty("pilot_id")
    Integer pilotId;
}
