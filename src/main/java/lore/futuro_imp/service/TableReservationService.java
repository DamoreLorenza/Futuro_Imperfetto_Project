package lore.futuro_imp.service;

import lore.futuro_imp.entities.TableReservation;
import lore.futuro_imp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lore.futuro_imp.payloads.TableReservationDTO;
import lore.futuro_imp.repositories.TableReservationRepository;
import java.util.UUID;

@Service
public class TableReservationService {

    @Autowired
    private TableReservationRepository tableReservationRepository;

    public Page<TableReservation> getTableReservation(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return tableReservationRepository.findAll(pageable);
    }

    public TableReservation save(TableReservationDTO body){

        TableReservation newTableReservation = new TableReservation();
        newTableReservation.setId(body.id());
        newTableReservation.setDate(body.date());
        newTableReservation.setTime(body.time());
        newTableReservation.setUser( body.user());
        newTableReservation.setDesk(body.desk());
        newTableReservation.setGame(body.game());
        newTableReservation.setEvent(body.event());

        return tableReservationRepository.save(newTableReservation);
    }

    public TableReservation findById(UUID id){
        return tableReservationRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id){
        TableReservation found = this.findById(id);
        tableReservationRepository.delete(found);
    }

    public TableReservation findbyIdAndUpdate(UUID id, TableReservation body){
        TableReservation found = this.findById(id);
        found.setDate(body.getDate());
        found.setTime(body.getTime());
        found.setUser(body.getUser());
        found.setDesk(body.getDesk());
        found.setGame(body.getGame());
        found.setEvent(body.getEvent());
        return tableReservationRepository.save(found);

    }

}
