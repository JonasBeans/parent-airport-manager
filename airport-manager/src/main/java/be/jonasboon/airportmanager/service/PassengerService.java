package be.jonasboon.airportmanager.service;

import be.jonasboon.airportmanager.dto.PassengerDTO;
import be.jonasboon.airportmanager.mapper.PassengerMapper;
import be.jonasboon.airportmanager.model.Passenger;
import be.jonasboon.airportmanager.repository.PassengerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(PassengerMapper::toDto)
                .collect(Collectors.toList());
    }

    public PassengerDTO getPassenger(Integer id){
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("No passenger found with id: %s", id)));
        return PassengerMapper.toDto(passenger);
    }

    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        passengerDTO.hasNoNull();
        Passenger passenger = PassengerMapper.toEntity(passengerDTO);
        return PassengerMapper.toDto(passengerRepository.save(passenger));
    }

    public PassengerDTO updatePassenger(Integer id, PassengerDTO passengerDTO) {
        if(passengerRepository.findById(id).isPresent()){
           passengerDTO.hasNoNull();
           return PassengerMapper.toDto(
                   passengerRepository.save(PassengerMapper.toEntity(id, passengerDTO)));
        } else throw new ResponseStatusException(NOT_FOUND, format("No passenger found with id: %s", id));
    }

    public void deletePassenger(Integer id) {
        if(passengerRepository.findById(id).isPresent()){
            passengerRepository.deleteById(id);
        } else throw new ResponseStatusException(NOT_FOUND, format("No passenger found with id: %s", id));
    }
}
