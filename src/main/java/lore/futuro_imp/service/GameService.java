package lore.futuro_imp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lore.futuro_imp.exceptions.NotFoundException;
import lore.futuro_imp.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lore.futuro_imp.payloads.NewGameDTO;
import lore.futuro_imp.repositories.GameRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Game> getGame(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return gameRepository.findAll(pageable);
    }


    public Game findById(UUID id) {
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

  //  public Game findGameIdByName(String name) {
   // /    return gameRepository.findGameIdByName(name).orElseThrow(() -> new NotFoundException(name));
  //  }
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

    public String uploadPicture(MultipartFile file) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
