package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CreateFlightDTO {

    @JsonProperty("id")
    String id;

    @JsonProperty("pilot_id")
    Integer pilotId;
}
