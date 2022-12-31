package be.jonasboon.fileinputconverter.exception;

public class InvalidFligthFromFile extends RuntimeException{
    public InvalidFligthFromFile(String message) {
        super("Flight from file is invalid:\n" + message);
    }
}
