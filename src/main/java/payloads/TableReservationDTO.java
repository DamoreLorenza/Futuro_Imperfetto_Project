package payloads;

import entities.Desk;
import entities.Event;
import entities.Game;
import entities.User;
import java.time.LocalDate;
import java.util.UUID;

public record TableReservationDTO(
        UUID id,
        LocalDate date,
        User user,
        Event event,
        Game game,
        Desk desk
) {
}
