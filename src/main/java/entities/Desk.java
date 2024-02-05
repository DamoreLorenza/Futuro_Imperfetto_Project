package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "table_number")
    private double tableNumber;

    @Column(name = "seats")
    private double seats;

    @OneToMany
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_game")
    @JsonIgnore
    private Game game;



}
