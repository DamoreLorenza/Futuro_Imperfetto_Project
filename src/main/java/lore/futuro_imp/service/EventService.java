package lore.futuro_imp.service;

import lore.futuro_imp.exceptions.NotFoundException;
import lore.futuro_imp.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lore.futuro_imp.payloads.NewEventDTO;
import lore.futuro_imp.repositories.EventRepository;

import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;


    public Page<Event> getEvent(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventRepository.findAll(pageable);
    }


    public Event findById(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Event found = this.findById(id);
        eventRepository.delete(found);
    }

    public Event save(NewEventDTO body) {
        Event newEvent = new Event();
        newEvent.setId(body.id());
        newEvent.setName(body.name());
        newEvent.setDescription(body.description());
        newEvent.setDate(body.date());
        newEvent.setAvatar("https://ui-avatars.com/api/?name=" + newEvent.getName());
        newEvent.setNumeroPartecipanti(body.numeroPartecipanti());
        return eventRepository.save(newEvent);
    }

    public Event findByIdAndUpdate(UUID id, NewEventDTO body) {
        Event found = this.findById(id);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setDate(body.date());
        found.setAvatar("https://ui-avatars.com/api/?name=" + found.getName());
        found.setNumeroPartecipanti(body.numeroPartecipanti());
        return eventRepository.save(found);
    }
}
