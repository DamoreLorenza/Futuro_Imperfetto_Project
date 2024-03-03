package lore.futuro_imp.controller;

import lore.futuro_imp.exceptions.BadRequestException;
import lore.futuro_imp.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lore.futuro_imp.payloads.NewEventDTO;
import lore.futuro_imp.payloads.NewEventResponseDTO;
import lore.futuro_imp.service.EventService;
import lore.futuro_imp.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@RestController
@RequestMapping("/event")
public class EventController {

        @Autowired
        private EventService eventService;

        @Autowired
        private UserService userService;

        @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping("/{eventId}")
        public Event getEventById(@PathVariable UUID eventId) {
            return eventService.findById(eventId);
        }

       @PreAuthorize("hasAnyAuthority('ADMIN','USER' ,'ROLE_ANONYMOUS')")
        @GetMapping
        public Page<Event> getEvent(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
            return eventService.getEvent(page, size, orderBy);
        }


        @PostMapping
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        @ResponseStatus(HttpStatus.CREATED)
        public NewEventResponseDTO createEvent(@RequestBody @Validated NewEventDTO newEventPayload, BindingResult validation) {
            System.out.println(validation);
            if (validation.hasErrors()) {
                throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
            }
            Event newEvent = eventService.save(newEventPayload);
            return new NewEventResponseDTO(newEvent.getId());
        }

        @PutMapping("/{eventId}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        public Event getEventByIdAndUpdate(@PathVariable UUID eventId, @RequestBody NewEventDTO modifiedEventPayload) {
            return eventService.findByIdAndUpdate(eventId, modifiedEventPayload);
        }

        @DeleteMapping("/{eventId}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void getEventByIdAndDelete(@PathVariable UUID eventId) {
            eventService.findByIdAndDelete(eventId);
        }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) throws IOException {
        return eventService.uploadPicture(file);
    }


    }
