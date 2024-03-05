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
import java.util.stream.Collectors;

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
        User user = userService.findById(body.idUser());

        List<Desk> desks = new ArrayList<>();
        body.idDesk().forEach(deskId -> desks.add(deskService.findById(deskId)));

        List<Game> games = new ArrayList<>();
        body.idGame().forEach(gameId -> games.add(gameService.findById(gameId)));

        TableReservation newTableReservation = new TableReservation();
        newTableReservation.setId(body.id());
        newTableReservation.setDate(body.date());
        newTableReservation.setTime(body.time());
        newTableReservation.setUser(user);
        newTableReservation.setDesk(desks);
        newTableReservation.setGame(games);

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


    public TableReservationDTO findCompleteById(UUID id) {
        TableReservation reservation = tableReservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found with ID: " + id));

        List<UUID> deskIds = reservation.getDesk().stream()
                .map(Desk::getId)
                .collect(Collectors.toList());

        List<UUID> gameIds = reservation.getGame().stream()
                .map(Game::getId)
                .collect(Collectors.toList());

        return new TableReservationDTO(
                reservation.getId(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getUser().getId(),
                gameIds,
                deskIds
        );
    }

  /*  public CombinedReservationDTO getCombinedReservationById(UUID reservationId) {

        TableReservation tableReservation = tableReservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata con ID: " + reservationId));

        List<String> deskNames = tableReservation.getDesk().stream().map(Desk::getName).collect(Collectors.toList());
        List<String> gameNames = tableReservation.getGame().stream().map(Game::getName).collect(Collectors.toList());

        CombinedReservationDTO combinedReservationDTO = new CombinedReservationDTO();
        combinedReservationDTO.setId(tableReservation.getId());
        combinedReservationDTO.setDate(tableReservation.getDate());
        combinedReservationDTO.setTime(tableReservation.getTime());
        combinedReservationDTO.setIdUser(tableReservation.getUser().getId());
        combinedReservationDTO.setDeskNames(deskNames);
        combinedReservationDTO.setGameNames(gameNames);

        return combinedReservationDTO;
    }*/
       }
