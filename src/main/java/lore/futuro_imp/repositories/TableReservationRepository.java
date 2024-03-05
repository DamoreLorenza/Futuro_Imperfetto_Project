package lore.futuro_imp.repositories;

import jakarta.transaction.Transactional;
import lore.futuro_imp.entities.TableReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, UUID> {
    Optional<TableReservation> findById(UUID id);

  /*  @Transactional
    @Modifying
    @Query("UPDATE TableReservation tr " +
            "SET tr.desk_id = (SELECT trd.desk_id FROM TableReservationDesk trd WHERE trd.tableReservationId = tr.idTableReservation)")
    void updateDeskIdFromTableReservationDesk();

*/

}
