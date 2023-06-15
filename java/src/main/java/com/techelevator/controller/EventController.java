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

    @PostMapping(path="/add/group_id={groupId}")
    public Event addNewEvent(@RequestBody Event newEvent,@PathVariable int groupId) {
        Event event = eventDao.addNewEvent(newEvent, groupId);
        eventDao.addToGroupsEvents(groupId, event.getEventId());
        return event;
    }

    @GetMapping(path="/{eventId}")
    public Event getEventDetails(@PathVariable int eventId) {
        return eventDao.getEventDetails(eventId);
    }

    @GetMapping(path="/future/{groupId}")
    public List<Event> getFutureEventsByGroupId(@PathVariable int groupId) {
        return eventDao.getFutureEventsByGroupId(groupId);
    }

    @PutMapping(path="/{eventId}")
    public Event updateEventDetails(@PathVariable int eventId, @RequestBody Event event){
        return eventDao.updateEventDetails(event, eventId);
    }
    @DeleteMapping(path="/delete/?creatorId={creatorId}&eventId={eventId}")
    public boolean deleteEvent (@RequestParam int creatorId, @RequestParam int eventId){
        return eventDao.deleteEvent(creatorId, eventId);
    }
}
