package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Reservation;
import enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "table")
@NoArgsConstructor
public class Desk {
    @Id
    @GeneratedValue
    @Column(name = "id_desk")
    private UUID id;

    @Column(name = "table_number")
    private double tableNumber;

    @Column(name = "seats")
    private double seats;

    @Enumerated(EnumType.STRING)
    private Reservation reservation;

    @OneToMany
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_game")
    @JsonIgnore
    private Game game;



}
