package lore.futuro_imp.payloads;

import jakarta.validation.constraints.NotNull;
import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.entities.Event;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.entities.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record TableReservationDTO(
        UUID id,
        @NotNull(message = "Data obbligatoria!")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        @NotNull(message = "Orario obbligatorio!")
        LocalTime time,
        @NotNull(message = "User obbligatorio!")
        //     User user,
        UUID idUser,

        //  Game game,

      //  UUID idEvent,
        UUID idGame,
        @NotNull(message = "Tavolo obbligatorio!")
       UUID idDesk
        //  Desk desk
) {
}
