package be.jonasboon.airportmanager.common;

import be.jonasboon.airportmanager.dto.AircraftDTO;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.lang.String.format;

public class AircraftTestConstants {
    public static AircraftDTO getTestAircraft(){
        return AircraftDTO.builder()
                .withMaxOccupation(0)
                .withCallSign("N5432")
                .withType("Skyhawk 172")
                .withModel("Cessna")
                .withModelYear("2022")
                .build();
    }
    public static AircraftDTO createTestAircraft(){
        return AircraftDTO.builder()
                .withMaxOccupation(10)
                .withCallSign("N9987")
                .withType("Beechcraft")
                .withModel("King Air")
                .withModelYear("2023")
                .build();
    }
    private static final String aircraftBaseUrl = "/api/v1/aircraft";
    public static final String GET_ALL_AIRCRAFT = aircraftBaseUrl + "/all";
    public static final String POST_AIRCRAFT = aircraftBaseUrl;
    public static final UnaryOperator<String> GET_AIRCRAFT_BY_CALLSIGN = (callsign) -> format("%s/%s", aircraftBaseUrl, callsign);
    public static final UnaryOperator<String> PUT_AIRCRAFT_BY_CALLSIGN = (callsign) -> format("%s/%s", aircraftBaseUrl ,callsign);
    public static final UnaryOperator<String> DELETE_AIRCRAFT_BY_CALLSIGN = (callsign) ->  format("%s/%s", aircraftBaseUrl, callsign);
}
