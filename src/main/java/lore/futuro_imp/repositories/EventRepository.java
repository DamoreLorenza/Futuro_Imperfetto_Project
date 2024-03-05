package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.Event;
import lore.futuro_imp.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByDateOrderByDateAsc(LocalDate date);
    Optional<Event> findById(UUID id);

}