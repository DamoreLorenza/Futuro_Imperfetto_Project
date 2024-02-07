package controller;

import entities.TableReservation;
import exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.TableReservationDTO;
import payloads.TableReservationResponseDTO;
import service.TableReservationService;
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

    @GetMapping("/{id}")
    public TableReservation findById(@PathVariable UUID id){
        return tableReservationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TableReservationResponseDTO createTableReservation(@RequestBody @Validated TableReservationDTO newTableReservationPayload, BindingResult validation){
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
