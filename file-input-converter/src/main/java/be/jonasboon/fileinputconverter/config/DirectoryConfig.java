package be.jonasboon.fileinputconverter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DirectoryConfig {

    @Value("${incoming_flights_directory}")
    String incomingFlightsDirectory;

}
