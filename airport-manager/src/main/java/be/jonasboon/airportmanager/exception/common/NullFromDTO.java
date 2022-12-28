package be.jonasboon.airportmanager.exception.common;

public class NullFromDTO extends RuntimeException{
    public NullFromDTO(String nullField) {
        super("The DTO has null values. " + nullField + " is null");
    }
}
