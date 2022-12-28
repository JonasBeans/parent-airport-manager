package be.jonasboon.airportmanager.controller;

import be.jonasboon.airportmanager.dto.CreateFlightDTO;
import be.jonasboon.airportmanager.dto.FlightDTO;
import be.jonasboon.airportmanager.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("api/v1/flight")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public List<FlightDTO> getAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping("/{flight_id}")
    public FlightDTO getFlightById(@PathVariable("flight_id") String flightId){
        return flightService.getFlightById(flightId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, format("No flight found with flight id: %s", flightId)));
    }

    @PostMapping("")
    public FlightDTO createFlight(@RequestBody CreateFlightDTO createFlightDTO){
        return flightService.createFlight(createFlightDTO);
    }
}
