package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Event;
import edu.uph.ii.ppproject.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class EventController {
    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public String EventList(Model model){
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);

        return "events/events";
    }
}
