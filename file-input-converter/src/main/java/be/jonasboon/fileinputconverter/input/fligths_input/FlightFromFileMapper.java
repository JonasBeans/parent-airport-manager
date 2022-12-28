package be.jonasboon.fileinputconverter.input.fligths_input;

public class FlightFromFileMapper {

    public static FlightFromFile fromFileFlightToFlight(String line){
        return FlightFromFile.builder()
                .input(line)
                .build();
    }
}
