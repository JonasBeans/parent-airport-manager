package be.jonasboon.fileinputconverter.input;

import be.jonasboon.fileinputconverter.config.DirectoryConfig;
import be.jonasboon.fileinputconverter.exception.InvalidFligthFromFile;
import be.jonasboon.fileinputconverter.input.fligths_input.FlightFromFileDTO;
import be.jonasboon.fileinputconverter.input.fligths_input.FlightFromFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
public class FlightsFromFileInput extends InputConverter<FlightFromFileDTO>{
    public FlightsFromFileInput(DirectoryConfig directoryConfig) {
        super(directoryConfig.getIncomingFlightsDirectory());
    }

    @Override
    FlightFromFileDTO mapLineToObject(String lineFromFile) {
        String[] slicedString = StringUtils.split(lineFromFile, ",");
        if(slicedString.length > 0)
            return FlightFromFileMapper.fromFileFlightToFlight(slicedString);
        else
            throw new InvalidFligthFromFile("Line in file is null.");
    }

}

