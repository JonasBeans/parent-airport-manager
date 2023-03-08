package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
public class AircraftDTO {
    @JsonProperty("callsign")
    String callSign;
    @JsonProperty("model")
    String model;
    @JsonProperty("type")
    String type;
    @JsonProperty("model_year")
    String modelYear;
    @JsonProperty("maximum_occupation")
    Integer maxOccupation;
}
