package controller;

import Exceptions.BadRequestException;
import entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.NewGameDTO;
import payloads.NewGameResponseDTO;
import service.GameService;
import service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {
        @Autowired
        private GameService gameService;

        @Autowired
        private UserService userService;

        @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping("/{gameId}")
        public Game getGameById(@PathVariable UUID gameId) {
            return gameService.findById(gameId);
        }

        @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping
        public Page<Game> getGame(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy) {
            return gameService.getGame(page, size, orderBy);
        }


        @PostMapping
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        @ResponseStatus(HttpStatus.CREATED)
        public NewGameResponseDTO createGame(@RequestBody @Validated NewGameDTO newGamePayload, BindingResult validation) {
            System.out.println(validation);
            if (validation.hasErrors()) {
                throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
            }
            Game newGame = gameService.save(newGamePayload);
            return new NewGameResponseDTO(newGame.getId());
        }

        @PutMapping("/{gameId}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        public Game getGameByIdAndUpdate(@PathVariable UUID gameId, @RequestBody NewGameDTO modifiedGamePayload) {
            return gameService.findByIdAndUpdate(gameId, modifiedGamePayload);
        }

        @DeleteMapping("/{gameId}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void getGameByIdAndDelete(@PathVariable UUID gameId) {
            gameService.findByIdAndDelete(gameId);
        }



}
