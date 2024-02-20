package lore.futuro_imp.payloads;

import lore.futuro_imp.entities.Event;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.entities.User;
import lore.futuro_imp.enums.Reservation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewDeskDTO(
        @NotNull(message = "Il numero del tavolo è obbligatorio")
        @Min(value = 1, message = "Il numero del tavolo deve essere maggiore di 0")
        @Max(value = 12, message = "Il numero del tavolo non può superare 12")
        Integer tableNumber,
       @NotNull(message = "Il numero di posti è obbligatorio")
        @Min(value = 1, message = "Il numero dei posti deve essere maggiore di 0")
        Integer seats,
        Reservation reservation

      //  @NotEmpty(message = "inserire credenziali utente")
       // User user,

      //  Game game,
      //  Event event
) {
}
