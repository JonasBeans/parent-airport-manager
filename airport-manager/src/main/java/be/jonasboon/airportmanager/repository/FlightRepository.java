package be.jonasboon.airportmanager.repository;

import be.jonasboon.airportmanager.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}
