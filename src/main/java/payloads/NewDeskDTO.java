package payloads;

import entities.Event;
import entities.Game;
import entities.User;
import enums.Reservation;
import enums.Role;

public record NewDeskDTO(
       // @NotNull(message = "Il numero del tavolo è obbligatorio")
       // @Min(value = 1, message = "Il numero del tavolo deve essere maggiore di 0")
       // @Max(value = 10, message = "Il numero del tavolo non può superare 10")
        Double tableNumber,
       // @NotNull(message = "Il numero di posti è obbligatorio")
       // @Min(value = 1, message = "Il numero dei posti deve essere maggiore di 0")
        Double seats,
        Reservation reservation,

        //  @NotEmpty(message = "inserire credenziali utente")
        User user,

        Game game,
        Event event
) {
}
