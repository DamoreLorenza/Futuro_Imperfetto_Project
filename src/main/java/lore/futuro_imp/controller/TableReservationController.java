package lore.futuro_imp.controller;

import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.entities.TableReservation;
import lore.futuro_imp.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lore.futuro_imp.payloads.TableReservationDTO;
import lore.futuro_imp.payloads.TableReservationResponseDTO;
import lore.futuro_imp.service.TableReservationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tableReservation")
public class TableReservationController {
    @Autowired
    private TableReservationService tableReservationService;


    @GetMapping
    public Page<TableReservation> getTableReservation(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String orderBy){
        return tableReservationService.getTableReservation(page, size, orderBy);
    }

  //  @GetMapping("/{id}")
  //  public TableReservation findById(@PathVariable UUID id){
  //      return tableReservationService.findById(id);
   // }

    @GetMapping("/{id}")
    public TableReservation findById(@PathVariable UUID id) {
        TableReservation tableReservation = tableReservationService.findById(id);

        // Carica i giochi associati alla tableReservation
        List<Game> games = tableReservation.getGame();

        // Carica le scrivanie associate alla tableReservation
        List<Desk> desks = tableReservation.getDesk();

        // Se necessario, caricare altre proprietà associate alla tableReservation

        // Restituisci la tableReservation con i giochi e le scrivanie associati
        return tableReservation;

    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public TableReservationResponseDTO createTableReservation(@RequestBody @Validated TableReservationDTO newTableReservationPayload, BindingResult validation){
      //  System.out.println("Payload JSON ricevuto: " + newTableReservationPayload);
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
        }
        TableReservation newTableReservation = tableReservationService.save(newTableReservationPayload);
        return new TableReservationResponseDTO(newTableReservation.getId());

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TableReservation findByIdAndUpdate(@PathVariable UUID id, @RequestBody TableReservation updateTableReservationPayload){
        return tableReservationService.findbyIdAndUpdate(id,updateTableReservationPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID id){
        tableReservationService.findByIdAndDelete(id);
    }

}
