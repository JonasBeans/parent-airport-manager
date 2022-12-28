package be.jonasboon.airportmanager.service;

import be.jonasboon.airportmanager.dto.AircraftDTO;
import be.jonasboon.airportmanager.mapper.AircraftMapper;
import be.jonasboon.airportmanager.model.Aircraft;
import be.jonasboon.airportmanager.repository.AircraftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.jonasboon.airportmanager.mapper.AircraftMapper.toDTO;
import static be.jonasboon.airportmanager.mapper.AircraftMapper.toEntity;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Optional<AircraftDTO> getAircraftById(String id) {
        return aircraftRepository.findById(id)
                .map(AircraftMapper::toDTO);
    }

    public List<AircraftDTO> getAllAircrafts() {
        return aircraftRepository.findAll().stream()
                .map(AircraftMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AircraftDTO createAircraft(AircraftDTO aircraftDTO) {
        aircraftDTO.hasNoNull();
        Aircraft aircraft = aircraftRepository.save(toEntity(aircraftDTO));
        return toDTO(aircraft);
    }

    public AircraftDTO updateAircraft(String callsign, AircraftDTO aircraftDTO) {
        if(aircraftRepository.findById(callsign).isPresent()){
            aircraftDTO.hasNoNullForUpdate();
            Aircraft aircraft = aircraftRepository.save(toEntity(callsign, aircraftDTO));
            return toDTO(aircraft);
        } else throw new ResponseStatusException(NOT_FOUND, String.format("No aircraft found with that id: %s", callsign));
    }

    public ResponseEntity<HttpStatus> deleteAircraft(String callsign) {
        if(aircraftRepository.findById(callsign).isPresent()){
            aircraftRepository.deleteById(callsign);
            return new ResponseEntity<>(OK);
        } else throw new ResponseStatusException(NOT_FOUND, String.format("No aircraft found with that id: %s", callsign));
    }
}
