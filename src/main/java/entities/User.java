package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(unique = true)
    private String email;

    private String password;

    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToOne
    @JoinColumn(name = "id_event")
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "table_number")
    @JsonIgnore
    private Desk desk;


}
