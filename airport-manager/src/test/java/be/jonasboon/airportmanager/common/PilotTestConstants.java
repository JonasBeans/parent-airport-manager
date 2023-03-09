package be.jonasboon.airportmanager.common;

import be.jonasboon.airportmanager.dto.PilotDTO;

import java.util.function.UnaryOperator;

import static java.lang.String.format;

public class PilotTestConstants {
    public static PilotDTO getTestPilot(){
        return PilotDTO.builder()
                .withId(1)
                .withFirstName("first_name_test")
                .withLastName("last_name_test")
                .build();
    }
    public static PilotDTO createTestPilot(){
        return PilotDTO.builder()
                .withFirstName("first_name_test_create")
                .withLastName("last_name_test_create")
                .build();
    }
    private static final String pilotBaseUrl = "/api/v1/pilot";
    public static final String GET_ALL_PILOT = pilotBaseUrl + "/all";
    public static final String POST_PILOT = pilotBaseUrl;
    public static final UnaryOperator<String> GET_PILOT_BY_ID = (id) -> format("%s?pilot_id=%s", pilotBaseUrl, id);
    public static final UnaryOperator<String> PUT_PILOT_BY_ID = (id) -> format("%s/%s", pilotBaseUrl, id);
    public static final UnaryOperator<String> DELETE_PILOT_BY_ID = (id) ->  format("%s/%s", pilotBaseUrl, id);
}
