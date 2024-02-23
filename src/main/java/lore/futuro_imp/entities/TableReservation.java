package lore.futuro_imp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@ToString
@Entity
@Table(name = "table_reservation")
@NoArgsConstructor
public class TableReservation {

    @Id
    @GeneratedValue
    @Column(name = "id_tableReservation")
    private UUID id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "Time")
    private LocalTime time;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_desk")
    private Desk desk;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;


    @OneToOne
    @JoinColumn(name = "id_game")
    private Game game;

}

