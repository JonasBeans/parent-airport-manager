package be.jonasboon.fileinputconverter.input.fligths_input;


import lombok.Builder;

@Builder
public class FlightFromFileDTO {
    String input;

    @Override
    public String toString() {
        return this.input;
    }
}
