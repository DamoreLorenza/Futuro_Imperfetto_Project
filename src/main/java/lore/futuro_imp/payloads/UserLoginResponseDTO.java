package lore.futuro_imp.payloads;

import lore.futuro_imp.entities.User;

public record UserLoginResponseDTO(String token, User user) {
}
