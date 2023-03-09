package be.jonasboon.airportmanager.integration;

import be.jonasboon.airportmanager.common.AbstractIntegrationTest;
import be.jonasboon.airportmanager.controller.PilotController;
import be.jonasboon.airportmanager.dto.PilotDTO;
import be.jonasboon.airportmanager.model.Pilot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static be.jonasboon.airportmanager.common.PilotTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
public class PilotIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    private PilotController pilotController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public PilotIntegrationTests(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Test
    void getPilotFromDatabase(){
        PilotDTO response = pilotController.getPilotById("1");

        assertThat(response.getFirstName()).isEqualTo("first_name_test");
        assertThat(response.getLastName()).isEqualTo("last_name_test");
    }

    @Test
    void GET_GetPilotByIdAPI_return200() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(GET_PILOT_BY_ID.apply("1")))
                .andExpect(status().isOk())
                .andReturn();

        PilotDTO response = fromMvcResult(mvcResult, PilotDTO.class);

        assertThat(response.getFirstName()).isEqualTo("first_name_test");
        assertThat(response.getLastName()).isEqualTo("last_name_test");
    }

    @Test
    void GET_GetAllPilotAPI_return200() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_PILOT))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapperForList = new ObjectMapper();
        List<PilotDTO> response = objectMapperForList.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PilotDTO>>() {});
        PilotDTO pilotDTO = response.stream()
                .filter(pilot -> 1 == pilot.getId())
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No aircraft found with matching callsign"));

        assertThat(pilotDTO.getFirstName()).isEqualTo("first_name_test");
        assertThat(pilotDTO.getLastName()).isEqualTo("last_name_test");
    }

    @Test
    void POST_CreatePilotAPI_return200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(POST_PILOT)
                        .content(toJson(createTestPilot()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        PilotDTO response = pilotController.getPilotById("2");

        assertThat(response.getFirstName()).isEqualTo("first_name_test");
        assertThat(response.getLastName()).isEqualTo("last_name_test");
    }

    @Test
    void PUT_UpdatePilotAPI_return200() throws Exception {
        PilotDTO testAircraft = new PilotDTO();
        testAircraft.setFirstName("put_test_first_name");
        testAircraft.setLastName("put_test_last_name");

        this.mockMvc.perform(MockMvcRequestBuilders.put(PUT_PILOT_BY_ID.apply("2"))
                        .content(toJson(testAircraft))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PilotDTO response = pilotController.getPilotById("2");

        assertThat(response.getFirstName()).isEqualTo("put_test_first_name");
        assertThat(response.getLastName()).isEqualTo("put_test_last_name");
    }

    @Test
    void DELETE_deletePilotAPI_return200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_PILOT_BY_ID.apply("1")))
                .andExpect(status().isOk());

        assertThatThrownBy(() ->
                pilotController.getPilotById("1"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("No pilot data found for pilot with id: 1");
    }
    @Test
    void GET_PilotById_Throws_Not_Found_Exception(){
        assertThatThrownBy(() ->
                pilotController.getPilotById("6969"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND \"No pilot data found for pilot with id: 6969\"");
    }
}
