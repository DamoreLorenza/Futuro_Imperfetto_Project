package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, UUID> {
    Optional<TableReservation> findById(UUID uuid);
}
