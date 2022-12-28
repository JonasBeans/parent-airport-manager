package be.jonasboon.airportmanager.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "pilot", schema = "application")
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Pilot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

}
