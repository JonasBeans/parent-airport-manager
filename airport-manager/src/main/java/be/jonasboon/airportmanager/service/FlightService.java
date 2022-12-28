package be.jonasboon.airportmanager.service;

import be.jonasboon.airportmanager.dto.CreateFlightDTO;
import be.jonasboon.airportmanager.dto.FlightDTO;
import be.jonasboon.airportmanager.exception.pilot.PilotNotFoundException;
import be.jonasboon.airportmanager.mapper.FlightMapper;
import be.jonasboon.airportmanager.mapper.PilotMapper;
import be.jonasboon.airportmanager.model.Flight;
import be.jonasboon.airportmanager.model.Pilot;
import be.jonasboon.airportmanager.repository.FlightRepository;
import be.jonasboon.airportmanager.repository.PilotRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.jonasboon.airportmanager.mapper.FlightMapper.toDto;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final PilotRepository pilotRepository;

    public FlightService(FlightRepository flightRepository, PilotRepository pilotRepository) {
        this.flightRepository = flightRepository;
        this.pilotRepository = pilotRepository;
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<FlightDTO> getFlightById(String id) {
        return flightRepository.findById(id)
                .map(FlightMapper::toDto);
    }

    public FlightDTO createFlight(CreateFlightDTO createFlightDTO) {
        Pilot pilot =  pilotRepository.findById(createFlightDTO.getPilotId())
                .orElseThrow(() -> new PilotNotFoundException(createFlightDTO.getPilotId()));

        return toDto(flightRepository.save(
                Flight.builder()
                .withId(createFlightDTO.getId())
                .withPilot(pilot)
                .build())
        );
    }
}
