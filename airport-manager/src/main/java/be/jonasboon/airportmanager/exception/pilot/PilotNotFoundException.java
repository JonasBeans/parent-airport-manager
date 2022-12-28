package be.jonasboon.airportmanager.exception.pilot;

public class PilotNotFoundException extends RuntimeException{
    public PilotNotFoundException(Integer pilotID) {
        super("No pilot found with id: " + pilotID);
    }
}
