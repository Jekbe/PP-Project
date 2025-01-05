package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Event;
import edu.uph.ii.ppproject.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventController {
    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public String eventList(Model model){
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);

        return "events/events";
    }

    @GetMapping("/eventForm")
    public String eventForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        Event event = id != null ? eventRepository.findById(id).orElse(new Event()) : new Event();

        model.addAttribute("event", event);

        return "events/eventForm";
    }

    @RequestMapping("/addEvent")
    public String addEvent(@ModelAttribute("event") Event event){
        eventRepository.save(event);

        return "redirect:/events";
    }

    @GetMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("Id") Long id){
        eventRepository.deleteById(id);

        return "redirect:/events";
    }

    @GetMapping("/eventInfo")
    public String eventInfo(Model model, @RequestParam("Id") Long id){
        Event event = eventRepository.getReferenceById(id);

        model.addAttribute("event", event);

        return "events/info";
    }
}
