package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.JdbcEventDao;
import com.techelevator.model.Event;
import com.techelevator.model.Group;
import com.techelevator.model.Match;
import com.techelevator.model.User;
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

    @GetMapping(path="/{groupId}")
    public List<Event> eventsByGroupId(@PathVariable int groupId) {
        return eventDao.getAllEventsByGroupId(groupId);
    }

    @PostMapping(path="/add/{groupId}")
    public Event addNewEvent(@RequestBody Event newEvent,@PathVariable int groupId) {
        Event event = eventDao.addNewEvent(newEvent, groupId);
        eventDao.addToGroupsEvents(groupId, event.getEventId());
        return event;
    }

    @GetMapping(path="/details/{eventId}")
    public Event getEventDetails(@PathVariable int eventId) {
        return eventDao.getEventDetails(eventId);
    }

    @GetMapping(path="/future/{groupId}")
    public List<Event> getFutureEventsByGroupId(@PathVariable int groupId) {
        return eventDao.getFutureEventsByGroupId(groupId);
    }

    @PutMapping(path="/update/{eventId}")
    public Event updateEventDetails(@PathVariable int eventId, @RequestBody Event event){
        return eventDao.updateEventDetails(event, eventId);
    }
    @DeleteMapping(path="/delete/{eventId}")
    public void deleteEvent(@PathVariable int eventId) { eventDao.deleteEvent(eventId); }

    @PostMapping(path="/{eventId}/join/{userId}")
    public void joinEvent(@PathVariable int eventId, @PathVariable int userId){
        eventDao.joinEvent(userId, eventId);
    }
    @GetMapping(path="/{eventId}/matches")
    public List<Match> getMatchesByEventId(@PathVariable int eventId) {
        return eventDao.getMatchesByEventId(eventId);
    }

    @GetMapping(path="/{eventId}/attending")
    public List<User> getPlayersByEventId(@PathVariable int eventId) {
        return eventDao.getPlayersByEventId(eventId);
    }

    @PostMapping(path="/{eventId}/create-match")
    public Match createNewMatchForEvent(@PathVariable int eventId, @RequestBody Match match){
        return eventDao.createNewMatchForEvent(eventId, match);
    }
}
