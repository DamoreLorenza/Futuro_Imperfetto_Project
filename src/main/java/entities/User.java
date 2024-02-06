package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
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


    @OneToOne
    @JoinColumn(name = "tableNumber")
    private Desk desk;


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
