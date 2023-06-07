package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.JdbcEventDao;
import com.techelevator.model.Event;
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

}
