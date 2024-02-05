package repositories;

import entities.Desk;
import entities.User;
import enums.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeskRepository extends JpaRepository<Desk, UUID> {

    List<Desk> findByReservation(Reservation reservation);

    List<Desk> findByTableNumber(double tableNumber);

}