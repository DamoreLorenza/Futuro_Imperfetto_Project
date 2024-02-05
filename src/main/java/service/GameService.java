package service;

import Exceptions.NotFoundException;
import entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import payloads.NewGameDTO;
import repositories.GameRepository;

import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserService userService;


    public Page<Game> getGame(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return gameRepository.findAll(pageable);
    }


    public Game findById(UUID id) {
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Game found = this.findById(id);
        gameRepository.delete(found);
    }

    public Game save(NewGameDTO body) {
        Game newGame = new Game();
        newGame.setId(body.id());
        newGame.setName(body.name());
        newGame.setDescription(body.description());
        newGame.setPlayers(body.players());
        newGame.setAvatar("https://ui-avatars.com/api/?name=" + newGame.getName());
        newGame.setReservation(body.reservation());
        return gameRepository.save(newGame);
    }

    public Game findByIdAndUpdate(UUID id, NewGameDTO body) {
        Game found = this.findById(id);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setPlayers(body.players());
        found.setAvatar("https://ui-avatars.com/api/?name=" + found.getName());
        found.setReservation(body.reservation());
        return gameRepository.save(found);
    }
}
