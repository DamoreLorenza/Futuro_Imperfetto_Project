package lore.futuro_imp.payloads;

import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.entities.Event;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

public record TableReservationDTO(
        UUID id,
        @NotEmpty(message = "Data obbligatoria!")
        LocalDate date,
        @NotEmpty(message = "Orario obbligatorio!")
        Time time,
        @NotEmpty(message = "User obbligatorio!")
        User user,
        Event event,
        Game game,
        @NotEmpty(message = "Tavolo obbligatorio!")
        Desk desk
) {
}
