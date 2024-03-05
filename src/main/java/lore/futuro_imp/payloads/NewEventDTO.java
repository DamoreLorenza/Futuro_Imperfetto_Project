package lore.futuro_imp.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventDTO(

        UUID id,
         @NotNull(message = "Nome obbligatorio")
        String name,
        String description,
        @NotNull(message = "date")
        LocalDate date,
        double numeroPartecipanti,

       String avatar
) {
}
