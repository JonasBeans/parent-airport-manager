package be.jonasboon.fileinputconverter.input.fligths_input;

public class FlightFromFileMapper {

    public static FlightFromFileDTO fromFileFlightToFlight(String[] flightObjectArray){
        return FlightFromFileDTO.builder()
                .input(flightObjectArray[0])
                .build();
    }
}
