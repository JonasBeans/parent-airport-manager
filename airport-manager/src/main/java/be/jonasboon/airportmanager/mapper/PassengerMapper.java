package be.jonasboon.airportmanager.mapper;

import be.jonasboon.airportmanager.dto.PassengerDTO;
import be.jonasboon.airportmanager.model.Passenger;

public class PassengerMapper {

    public static PassengerDTO toDto(Passenger passenger){
        return PassengerDTO.builder()
                .withId(passenger.getId())
                .withFirstName(passenger.getFirtsName())
                .withLastName(passenger.getLastName())
                .build();
    }

    public static Passenger toEntity(PassengerDTO passengerDTO){
         return Passenger.builder()
                 .withFirtsName(passengerDTO.getFirstName())
                 .withLastName(passengerDTO.getLastName())
                 .build();
    }

    public static Passenger toEntity(Integer id, PassengerDTO passengerDTO){
        return Passenger.builder()
                .withId(id)
                .withFirtsName(passengerDTO.getFirstName())
                .withLastName(passengerDTO.getLastName())
                .build();
    }
}
