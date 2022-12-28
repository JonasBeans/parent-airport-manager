package be.jonasboon.airportmanager.repository;

import be.jonasboon.airportmanager.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
