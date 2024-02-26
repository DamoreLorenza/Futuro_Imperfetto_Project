package lore.futuro_imp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lore.futuro_imp.enums.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "desk")
@NoArgsConstructor
public class Desk {
    @Id
    @GeneratedValue
    @Column(name = "id_desk")
    private UUID id;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "seats")
    private Integer seats;

    @Enumerated(EnumType.STRING)
    private Reservation reservation;




  //  @JsonIgnore
  //  @OneToMany(mappedBy = "desk")
  //  private List<TableReservation> tableReservation;

    @JsonIgnore
     @ManyToOne
       @JoinColumn(name = "id_tableReservation")
     private TableReservation tableReservation;



}
