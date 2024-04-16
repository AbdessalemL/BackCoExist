package tn.esprit.coexist.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carpoolingID")

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BookingID;

    private Integer nb;
    @ManyToOne
    @JsonIgnore
    private Carpooling carpooling;
    @ManyToOne (cascade = CascadeType.ALL)
    private User user;
    @OneToOne(mappedBy = "booking")
    @JsonIgnore
    private FeedBack feedBack;


}
