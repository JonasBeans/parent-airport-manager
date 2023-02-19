package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
public class PilotDTO {

    public PilotDTO() {
    }

    int id;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;

}
