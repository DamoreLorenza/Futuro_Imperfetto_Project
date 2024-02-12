package lore.futuro_imp.controller;

import lore.futuro_imp.entities.User;
import lore.futuro_imp.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lore.futuro_imp.payloads.ResponseDTO;
import lore.futuro_imp.payloads.UserDTO;
import lore.futuro_imp.payloads.UserLoginDTO;
import lore.futuro_imp.payloads.UserLoginResponseDTO;
import lore.futuro_imp.service.AuthService;
import lore.futuro_imp.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().toList().toString());
        } else {
            System.out.println(newUserPayload);
            User newUser = userService.save(newUserPayload);

            return new ResponseDTO(newUser.getId());
        }
    }
}