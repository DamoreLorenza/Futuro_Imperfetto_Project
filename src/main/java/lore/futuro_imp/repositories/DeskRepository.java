package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.enums.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeskRepository extends JpaRepository<Desk, UUID> {

    Optional<Desk> findDeskIdBySeats(Integer seats);
    Optional<Desk> findById(UUID id);

  //  @Query("SELECT d FROM Desk c WHERE d.desk.seats = :seatsDesk")
  //  List<Desk> getDeskBySeats(@Param("seatsDesk") Integer seatsDesk);

    Optional<Desk> findBySeats(Integer seats);

    List<Desk> findByReservation(Reservation reservation);

    List<Desk> findByTableNumber(double tableNumber);

}