package lore.futuro_imp.controller;

import lore.futuro_imp.enums.Role;
import lore.futuro_imp.exceptions.BadRequestException;
import lore.futuro_imp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lore.futuro_imp.payloads.UserDTO;
import lore.futuro_imp.payloads.UserResponseDTO;
import lore.futuro_imp.service.UserService;

import java.io.IOException;
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

   // @GetMapping("/{id}/role")
   // public User findByRoleId(@PathVariable Role role){
   //     return userService.findByRole(role);
   // }

  //  @GetMapping("/{id}/id-and-role")
    //public UserRoleResponseDTO findIdAndRoleById(@PathVariable UUID id){
      //  User user = userService.findById(id);
        //return new UserRoleResponseDTO(user.getId(), user.getRole());
   // }

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


     @PostMapping("/{id}/upload")
     @PreAuthorize("hasAuthority('ADMIN')")
     public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) throws IOException {
        return userService.uploadPicture(file);
    }


    //per dare la risposta al frontend sul ruolo dell'utente che sta eseguendo il login

}