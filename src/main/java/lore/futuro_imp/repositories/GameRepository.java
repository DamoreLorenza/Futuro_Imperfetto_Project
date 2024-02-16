package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.Game;
import lore.futuro_imp.enums.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
   // List<Game> findByReservation(Reservation reservation);
    Optional<Game> findById(UUID id);

   // Page<Game> getGame(Pageable pageable);

   // Page<Game> findByName(Pageable pageable);
}
