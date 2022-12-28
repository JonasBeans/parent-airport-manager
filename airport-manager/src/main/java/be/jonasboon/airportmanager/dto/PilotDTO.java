package be.jonasboon.airportmanager.dto;

import be.jonasboon.airportmanager.exception.common.NullFromDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Value
@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
public class PilotDTO {

    int id;

    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;

    public boolean hasNoNull(){
        if(this.getFirstName() == null){
            throw new NullFromDTO("first name");
        }
        if(this.getLastName() == null){
            throw new NullFromDTO("last name");
        }
        return true;
    }
}
