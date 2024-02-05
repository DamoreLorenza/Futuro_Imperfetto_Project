package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id_event")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    private String avatar;

    @JoinColumn(name = "desk")
    @JsonIgnore
    private Desk desk;


    @OneToMany(mappedBy = "event")
    private List<User> user;


}
