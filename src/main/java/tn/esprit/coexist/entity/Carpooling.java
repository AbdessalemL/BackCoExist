package tn.esprit.coexist.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carpoolingID")

public class Carpooling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carpoolingID;

    private LocalDateTime departureTime;
    private String longitudeDeparture;
    private String latitudeDestination;

    private String latitudeDeparture;
    private String longitudeDestination;



    private Integer availableSeats;
    private float costPerSeat;
    @Enumerated(EnumType.STRING)
    private Day day;
    LocalTime time;

    @Enumerated(EnumType.STRING)
    private CarpoolingType carpoolingType;
    private Long registrationNumber;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy ="carpooling",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking>bookings;
}
