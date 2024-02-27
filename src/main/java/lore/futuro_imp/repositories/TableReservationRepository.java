package lore.futuro_imp.repositories;

import lore.futuro_imp.entities.TableReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, UUID> {
    Optional<TableReservation> findById(UUID id);

  //  @Query("SELECT tr FROM TableReservation tr " +
  //          "JOIN TableReservationDesk trd ON tr.id = trd.tableReservation.id " +
  //          "JOIN TableReservationGame trg ON tr.id = trg.tableReservation.id")
 //   List<TableReservation> findByTableReservationDeskAndGame();



}
