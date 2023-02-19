package be.jonasboon.airportmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
