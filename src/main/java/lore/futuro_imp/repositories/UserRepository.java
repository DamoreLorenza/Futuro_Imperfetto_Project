package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.User;
import lore.futuro_imp.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID id);

    //Optional<User> findByRole(Role role);
   // @Query("SELECT u.role FROM User u WHERE u.id = :userId")
    //Optional<Role> findRoleById(@Param("userId") UUID userId);

    User findUserIdByEmail(String email);
    Optional<User> findByEmail(String email);
}