package lore.futuro_imp.payloads;

import lore.futuro_imp.enums.Reservation;
import jakarta.validation.constraints.NotNull;


import java.util.UUID;

public record NewGameDTO(
        UUID id,
       @NotNull(message = "Nome obbligatorio")
        String name,
        String description,
       @NotNull(message = "Players")
        Double players,

        Reservation reservation
) {
}