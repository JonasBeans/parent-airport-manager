package be.jonasboon.airportmanager.controller;

import be.jonasboon.airportmanager.dto.PilotDTO;
import be.jonasboon.airportmanager.service.PilotService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/v1/pilot")
public class PilotController {

    private final PilotService pilotService;


    public PilotController(PilotService pilotServie) {
        this.pilotService = pilotServie;
    }

    @GetMapping("")
    public PilotDTO getPilotById(@RequestParam(name = "pilot_id") String id){
        return pilotService.getPilotFromId(valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("No pilot data found for pilot with id: %s", id)));
    }

    @GetMapping("/all")
    public List<PilotDTO> getAllPilots(){
        return pilotService.getAllPilots();
    }

    @PostMapping("")
    public PilotDTO createPilot(@RequestBody PilotDTO pilotDTO){
        return pilotService.createPilot(pilotDTO);
    }

    @PutMapping("/{pilot_id}")
    public PilotDTO updatePilot(@PathVariable("pilot_id") String id,
                                @RequestBody PilotDTO pilotDTO){
        return pilotService.updatePilot(valueOf(id), pilotDTO);
    }

    @DeleteMapping("/{pilot_id}")
    public void deletePilot(@PathVariable("pilot_id") Integer id){
        pilotService.deletePilot(id);
    }
}
