package payloads;

import enums.Reservation;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventDTO(

        UUID id,
        //  @NotNull(message = "Nome obbligatorio")
        String name,
        String description,
        // @NotNull(message = "")
        LocalDate date,
        double numeroPartecipanti


) {
}
