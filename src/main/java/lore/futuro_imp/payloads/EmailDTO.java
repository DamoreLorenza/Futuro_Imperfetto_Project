package lore.futuro_imp.payloads;

import jakarta.validation.constraints.NotNull;

public record EmailDTO(
       @NotNull(message = "messaggio non può essere null")
        String messaggio,


       @NotNull(message = "oggetto non può essere null")
        String oggetto
) {
}