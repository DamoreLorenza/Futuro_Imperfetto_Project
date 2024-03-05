package lore.futuro_imp.service;

import lore.futuro_imp.entities.*;
import lore.futuro_imp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lore.futuro_imp.payloads.TableReservationDTO;
import lore.futuro_imp.repositories.TableReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TableReservationService {

    @Autowired
    private TableReservationRepository tableReservationRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private DeskService deskService;
    @Autowired
    private GameService gameService;
    @Autowired
    private EventService eventService;

    public Page<TableReservation> getTableReservation(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return tableReservationRepository.findAll(pageable);
    }

  public TableReservation save(TableReservationDTO body){
     //  Desk desk = deskService.findById(body.idDesk());
     //  Game game = gameService.findById(body.idGame());
       User user = userService.findById(body.idUser());

      List<Desk> desk = new ArrayList<>();
      desk.add(deskService.findById(body.idDesk()));

      List<Game> game = new ArrayList<>();
      game.add(gameService.findById(body.idGame()));

      TableReservation newTableReservation = new TableReservation();

        newTableReservation.setId(body.id());
        newTableReservation.setDate(body.date());
        newTableReservation.setTime(body.time());
        newTableReservation.setUser(userService.findById(body.idUser()));
        newTableReservation.setDesk(desk);
        newTableReservation.setGame(game);
      //  newTableReservation.setDesk(deskService.findById(body.idDesk()));
      //  newTableReservation.setGame(gameService.findById(body.idGame()));
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
        User user = userService.findById(body.getUser().getId());
     List<Desk> desk = new ArrayList<>();
     desk.add(deskService.findById(body.getId()));

     List<Game> game = new ArrayList<>();
     game.add(gameService.findById(body.getId()));
   //     Desk desk = deskService.findById(body.getDesk().getId());
   //     Game game = gameService.findById(body.getGame().getId());
       //  Event event = eventService.findById(body.getEvent().getId());
        TableReservation found = this.findById(id);
        found.setDesk(body.getDesk());
        found.setGame(body.getGame());
        found.setDate(body.getDate());
        found.setTime(body.getTime());
        found.setUser(userService.findById(body.getUser().getId()));
      //  found.setDesk(deskService.findById(body.getDesk().getId()));
      //  found.setGame(gameService.findById(body.getGame().getId()));
       //   found.setEvent(eventService.findById(body.getEvent().getId()));
        return tableReservationRepository.save(found);

    }



    // Metodo per recuperare tutte le prenotazioni del tavolo
    public List<TableReservation> getAllTableReservations() {
        return tableReservationRepository.findAll();
    }



 /*   public void updateDeskIdFromTableReservationDesk() {
        tableReservationRepository.updateDeskIdFromTableReservationDesk();
    }*/
       }
