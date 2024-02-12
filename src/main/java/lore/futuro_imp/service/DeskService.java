package lore.futuro_imp.service;

import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lore.futuro_imp.payloads.NewDeskDTO;
import lore.futuro_imp.repositories.DeskRepository;


import java.util.UUID;

@Service
public class DeskService {

    @Autowired
    private DeskRepository deskRepository;


    public Page<Desk> getDesk(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return deskRepository.findAll(pageable);
    }



        public Desk save(NewDeskDTO body){
        //    deskRepository.findByReservation(body.reservation()).ifPresent(desk -> {
            //throw new BadRequestException("Il tavolo " + desk.getTableNumber() + " è già prenotato!");
        //    });
            Desk newDesk = new Desk();
            newDesk.setTableNumber(body.tableNumber());
            newDesk.setSeats(body.seats());
            newDesk.setReservation(body.reservation());
            newDesk.setUser(body.user());
            return deskRepository.save(newDesk);
        }

        public Desk findById(UUID id){
            return deskRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        }

        public void findByIdAndDelete(UUID id){
            Desk found = this.findById(id);
            deskRepository.delete(found);
        }

        public Desk findbyIdAndUpdate(UUID id, Desk body){
            Desk found = this.findById(id);
            found.setTableNumber(body.getTableNumber());
            found.setSeats(body.getSeats());
            found.setReservation(body.getReservation());
            found.setUser(body.getUser());
            return deskRepository.save(found);

        }

  //  public Desk findByReservation(Reservation reservation) {
  //      return deskRepository.findByReservation(reservation).orElseThrow(() -> new NotFoundException("Prenotazione non trovata per " + reservation));
  //  }

   // public Desk findByTableNumber(Double tableNumber) {
     //   return deskRepository.findByTableNumber(tableNumber)
       //         .orElseThrow(() -> new NotFoundException("Nessun tavolo trovato con il numero " + tableNumber));
    //}
}
