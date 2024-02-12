package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.Game;
import lore.futuro_imp.enums.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    List<Game> findByReservation(Reservation reservation);
    Page<Game> findById(Pageable pageable);

    Page<Game> findByName(Pageable pageable);
}
