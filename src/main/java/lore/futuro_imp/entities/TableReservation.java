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
import java.util.List;
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

    @Column(name = "time")
    private LocalTime time;



    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;



    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "table_reservation_desk",
            joinColumns = @JoinColumn(name = "table_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "desk_id")
    )
    private List<Desk> desk;

    public List<Desk> getDesk() {
        return desk;
    };

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "table_reservation_game",
            joinColumns = @JoinColumn(name = "table_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> game;


    //  @OneToOne
    //  @JoinColumn(name = "id_desk")
    //  private Desk desk;


 /*   @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
*/

//    @OneToOne
//    @JoinColumn(name = "id_game")
//    private Game game;


}

