package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.enums.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeskRepository extends JpaRepository<Desk, UUID> {

    List<Desk> findByReservation(Reservation reservation);

    List<Desk> findByTableNumber(double tableNumber);

}