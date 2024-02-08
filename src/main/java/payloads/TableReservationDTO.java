package payloads;

import entities.Desk;
import entities.Event;
import entities.Game;
import entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record TableReservationDTO(
        UUID id,
        @NotEmpty(message = "Data obbligatoria!")
        LocalDate date,
        @NotEmpty(message = "User obbligatorio!")
        User user,
        Event event,
        Game game,
        @NotEmpty(message = "Tavolo obbligatorio!")
        Desk desk
) {
}
