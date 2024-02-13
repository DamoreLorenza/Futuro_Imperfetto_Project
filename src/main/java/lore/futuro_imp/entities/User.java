package lore.futuro_imp.entities;

import lore.futuro_imp.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User implements UserDetails {
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


    //@ManyToOne
    //@JoinColumn(name = "id_event")
   // @JsonIgnore
   // private Event event;

    @OneToMany(mappedBy = "user")
    private List<TableReservation> tableReservations;


 //   @OneToMany(mappedBy = "user")
 //   private List<Desk> desks;

   // @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

   // @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //@Override
    public boolean isAccountNonLocked() {
        return true;
    }

   // @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


  //  @Override
    public boolean isEnabled() {
        return true;
    }
}
