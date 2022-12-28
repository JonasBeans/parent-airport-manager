package be.jonasboon.airportmanager.dto;

import be.jonasboon.airportmanager.exception.common.NullFromDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class PassengerDTO {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;

    /**
     * @description: Method to check if new incoming DTO has no null values
     * @return true if DTO has no Null values
     */
    public boolean hasNoNull(){
        if(this.firstName == null){
            throw new NullFromDTO("first name");
        }
        if(this.lastName == null){
            throw new NullFromDTO("last name");
        } return true;
    }

    /**
     * @description: Method to check if incoming DTO has no null values
     * @return true if DTO has no Null values
     */
    public boolean hasNoNullForUpdate(){
        if(this.firstName == null){
            throw new NullFromDTO("first name");
        }
        if(this.lastName == null){
            throw new NullFromDTO("last name");
        } return true;
    }
}
