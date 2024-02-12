package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID uuid);

    Optional<User> findByEmail(String email);
}