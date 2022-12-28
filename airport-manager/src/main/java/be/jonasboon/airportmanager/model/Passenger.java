package be.jonasboon.airportmanager.model;

import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "passenger", schema = "application")
@Getter
@Builder(setterPrefix = "with")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "first_name")
    String firtsName;

    @Column(name = "last_name")
    String lastName;
}
