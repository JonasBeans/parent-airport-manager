package be.jonasboon.airportmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "aircraft", schema = "application")
@Getter
@Builder(setterPrefix = "with")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Aircraft {

    @Id
    @Column(name = "callsign")
    String callSign;

    @Column(name = "model")
    String model;

    @Column(name = "modeltype")
    String type;

    @Column(name = "modelyear")
    String modelYear;

    @Column(name = "maximum_occupation")
    Integer maxOccupation;
}
