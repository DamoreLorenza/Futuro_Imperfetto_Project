package service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import payloads.UserDTO;
import repositories.UserRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    private PasswordEncoder bcrypt =  new BCryptPasswordEncoder(11);
    public Page<User> getUser(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }

    public User save(UserDTO body){
        userRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email "+utente.getEmail()+ " è già in uso!");
        });
        User newUser = new User();
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + newUser.getName() + "+" + newUser.getSurname());
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());
        newUser.setPassword(bcrypt.encode(body.password()));
        if(body.role() != null){
            newUser.setRole(body.role());
        }

        return userRepository.save(newUser);
    }

    public User findById(UUID id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id){
        User found = this.findById(id);
        userRepository.delete(found);
    }

    public User findbyIdAndUpdate(UUID id, User body){
        User found = this.findById(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setUsername(body.getUsername());
        return userRepository.save(found);

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

   public String uploadPicture(MultipartFile file) throws IOException {
   String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
   return url;
    }

}