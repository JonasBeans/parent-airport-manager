package be.jonasboon.airportmanager.integration;

import be.jonasboon.airportmanager.common.AbstractIntegrationTest;
import be.jonasboon.airportmanager.controller.AircraftController;
import be.jonasboon.airportmanager.dto.AircraftDTO;
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

import static be.jonasboon.airportmanager.common.AircraftTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
class AircraftIntegrationTests extends AbstractIntegrationTest {
	@Autowired
	private AircraftController aircraftController;
	@Autowired
	private WebApplicationContext webApplicationContext;

	public AircraftIntegrationTests(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}

	@Test
	void getAircraftFromDatabase(){
		AircraftDTO response = aircraftController.getAircraftByCallsign("N5432");

		assertThat(response.getCallSign()).isEqualTo("N5432");
		assertThat(response.getType()).isEqualTo("Skyhawk 172");
		assertThat(response.getModel()).isEqualTo("Cessna");
		assertThat(response.getModelYear()).isEqualTo("2022");
	}

	@Test
	void GET_GetAircraftByCallSignAPI_return200() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(GET_AIRCRAFT_BY_CALLSIGN.apply("N5432")))
				.andExpect(status().isOk())
				.andReturn();

		AircraftDTO response = fromMvcResult(mvcResult, AircraftDTO.class);

		assertThat(response.getCallSign()).isEqualTo("N5432");
		assertThat(response.getType()).isEqualTo("Skyhawk 172");
		assertThat(response.getModel()).isEqualTo("Cessna");
		assertThat(response.getModelYear()).isEqualTo("2022");
	}

	@Test
	void GET_GetAllAircraftAPI_return200() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_AIRCRAFT))
				.andExpect(status().isOk())
				.andReturn();

		ObjectMapper objectMapperForList = new ObjectMapper();
		List<AircraftDTO> response = objectMapperForList.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<AircraftDTO>>() {});
		AircraftDTO aircraftDTO = response.stream()
				.filter(aircraft -> "N5432".equals(aircraft.getCallSign()))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("No aircraft found with matching callsign"));

		assertThat(aircraftDTO.getCallSign()).isEqualTo("N5432");
		assertThat(aircraftDTO.getType()).isEqualTo("Skyhawk 172");
		assertThat(aircraftDTO.getModel()).isEqualTo("Cessna");
		assertThat(aircraftDTO.getModelYear()).isEqualTo("2022");
	}

	@Test
	void POST_CreateAircraftAPI_return200() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post(POST_AIRCRAFT)
						.content(toJson(createTestAircraft()))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		AircraftDTO response = aircraftController.getAircraftByCallsign("N9987");

		assertThat(response.getCallSign()).isEqualTo("N9987");
		assertThat(response.getType()).isEqualTo("Beechcraft");
		assertThat(response.getModel()).isEqualTo("King Air");
		assertThat(response.getModelYear()).isEqualTo("2023");
	}

	@Test
	void PUT_UpdateAircraftAPI_return200() throws Exception {
		AircraftDTO testAircraft = new AircraftDTO();
		testAircraft.setMaxOccupation(2);
		testAircraft.setModel("Grob");
		testAircraft.setType("G103 Twin Astir");
		testAircraft.setModelYear("2021");

		String type = aircraftController.getAircraftByCallsign("N1234").getType();
		System.out.println("type = " + type);

		this.mockMvc.perform(MockMvcRequestBuilders.put(PUT_AIRCRAFT_BY_CALLSIGN.apply("N1234"))
						.content(toJson(testAircraft))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		AircraftDTO response = aircraftController.getAircraftByCallsign("N1234");

		assertThat(response.getCallSign()).isEqualTo("N1234");
		assertThat(response.getType()).isEqualTo("G103 Twin Astir").isNotEqualTo("Skyhawk 172");
		assertThat(response.getModel()).isNotEqualTo("Cessna").isEqualTo("Grob");
		assertThat(response.getModelYear()).isNotEqualTo("2022").isEqualTo("2021");;
	}

	@Test
	void DELETE_deleteAircraftAPI_return200() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_AIRCRAFT_BY_CALLSIGN.apply("N1234")))
				.andExpect(status().isOk());

		assertThatThrownBy(() ->
				aircraftController.getAircraftByCallsign("N1234"))
				.isInstanceOf(ResponseStatusException.class)
				.hasMessageContaining("No aircraft found with id: N1234");
	}
	@Test
	void GET_AircraftByCallSign_Throws_Not_Found_Exception(){
		assertThatThrownBy(() ->
				aircraftController.getAircraftByCallsign("N6969"))
			.isInstanceOf(ResponseStatusException.class)
			.hasMessageContaining("No aircraft found with id: N6969");
	}
 }
