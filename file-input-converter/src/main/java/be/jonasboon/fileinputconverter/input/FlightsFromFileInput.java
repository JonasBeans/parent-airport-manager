package be.jonasboon.fileinputconverter.input;

import be.jonasboon.fileinputconverter.input.fligths_input.FlightFromFile;
import be.jonasboon.fileinputconverter.input.fligths_input.FlightFromFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlightsFromFileInput extends InputConverter<FlightFromFile>{

    @Value("${incoming_flights_directory}")
    String incomingFlightsDirectory;

    @Override
    FlightFromFile mapObjectFromFile(String lineFromFile) {
        return FlightFromFileMapper.fromFileFlightToFlight(lineFromFile);
    }

    @Override
    public void setFileDirectory() {
        super.fileDirectory = incomingFlightsDirectory;
    }
}

