package controller;

import exceptions.BadRequestException;
import entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payloads.NewEventDTO;
import payloads.NewEventResponseDTO;
import service.EventService;
import service.UserService;

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

        @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
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



    }
