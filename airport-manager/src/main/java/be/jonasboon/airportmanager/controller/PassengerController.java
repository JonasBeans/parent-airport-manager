package be.jonasboon.airportmanager.controller;

import be.jonasboon.airportmanager.dto.PassengerDTO;
import be.jonasboon.airportmanager.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.valueOf;

@RestController
@RequestMapping("api/v1/passenger")
public class PassengerController {


    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/all")
    public List<PassengerDTO> getAllPassengers(){
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public PassengerDTO getPassengerById(@PathVariable("id") Integer id){
        return passengerService.getPassenger(id);
    }

    @PostMapping("")
    public PassengerDTO createPassenger(@RequestBody PassengerDTO passengerDTO){
        return passengerService.createPassenger(passengerDTO);
    }

    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@RequestBody PassengerDTO passengerDTO,
                                        @PathVariable("id") Integer id){
        return passengerService.updatePassenger(id, passengerDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") Integer id){
         passengerService.deletePassenger(id);
    }


}
