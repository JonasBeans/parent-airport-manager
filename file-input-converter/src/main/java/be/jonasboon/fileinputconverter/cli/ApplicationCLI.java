package be.jonasboon.fileinputconverter.cli;

import be.jonasboon.fileinputconverter.input.FlightsFromFileInput;
import be.jonasboon.fileinputconverter.input.fligths_input.FlightFromFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ApplicationCLI implements CommandLineRunner {

    @Autowired
    private FlightsFromFileInput inputFileConverter;

    @Override
    public void run(String... args) throws Exception {
        inputFileConverter.setFileDirectory();
        List<FlightFromFile> flights = inputFileConverter.getAllObjectsFromFile("inputFile.txt");
    }
}
