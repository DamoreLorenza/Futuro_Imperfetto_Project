package controller;

import Exceptions.BadRequestException;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.UserDTO;
import payloads.UserResponseDTO;
import service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy){
        return userService.getUser(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id){
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
        }
        User newUser = userService.save(newUserPayload);
        return new UserResponseDTO(newUser.getId());

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable UUID id, @RequestBody User updateUserPayload){
        return userService.findbyIdAndUpdate(id,updateUserPayload);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID id){
        userService.findByIdAndDelete(id);
    }

    //Endpoint per upload immagini
  //  @PostMapping("/{id}/upload")
   // @PreAuthorize("hasAuthority('ADMIN')")
   // public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable long id) throws IOException {
    //    return userService.uploadPicture(file);
   // }
}