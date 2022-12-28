package be.jonasboon.airportmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "flight", schema = "application")
@Getter
@Builder(setterPrefix = "with")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Flight {

    @Id
    @Column(name = "id")
    String id;

    @ManyToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;
}
