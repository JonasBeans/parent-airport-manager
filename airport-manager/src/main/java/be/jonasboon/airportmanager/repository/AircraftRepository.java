package be.jonasboon.airportmanager.repository;

import be.jonasboon.airportmanager.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, String> {

}
