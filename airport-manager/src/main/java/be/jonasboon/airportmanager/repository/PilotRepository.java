package be.jonasboon.airportmanager.repository;

import be.jonasboon.airportmanager.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Integer> {
}
