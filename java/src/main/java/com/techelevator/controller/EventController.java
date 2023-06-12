package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.JdbcEventDao;
import com.techelevator.model.Event;
import com.techelevator.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventDao eventDao;

//    public EventController(EventDao eventDao) { this.eventDao = eventDao; }

    @GetMapping(path="/group_id={groupId}")
    public List<Event> eventsByGroupId(@PathVariable int groupId) {
        return eventDao.getAllEventsByGroupId(groupId);
    }

    @GetMapping(path="/{eventId}")
    public Event getEventDetails(@PathVariable int eventId) {
        return eventDao.getEventDetails(eventId);
    }

    @PostMapping(path="/add")
    public void addNewEvent(@RequestBody Event newEvent, Group sponsorGroup) {eventDao.addNewEvent(newEvent, sponsorGroup);}
}
