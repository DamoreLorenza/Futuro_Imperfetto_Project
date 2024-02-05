package payloads;

import enums.Reservation;


import java.util.UUID;

public record NewGameDTO(
        UUID id,
      //  @NotNull(message = "Nome obbligatorio")
        String name,
        String description,
       // @NotNull(message = "Players")
        Double players,

       // @NotNull(message = "Stato fattura obbligatorio")
        Reservation reservation
) {
}