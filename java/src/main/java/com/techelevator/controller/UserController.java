package com.techelevator.controller;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.Match;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(JdbcUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param userId
     * @return User object of the corresponding userId
     */
    @GetMapping(path="/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userDao.getUserById(userId);
    }

    /**
     * @param userId
     * @return list of ALL Event objects that user has been part of
     */
    @GetMapping(path="/{userId}/events")
    public List<Event> getAllUserEvents(@PathVariable int userId) {
        return userDao.getAllUserEvents(userId);
    }

    /**
     * @param userId
     * @return list of future (from current logged in date/time) Event objects that user is part of
     */
    @GetMapping(path="/{userId}/future-events")
    public List<Event> getUpcomingUserEvents(@PathVariable int userId) {
        return userDao.getUpcomingUserEvents(userId);
    }

    /**
     * @param userId
     * @return a list of ALL Match objects that user has participated in so far
     */
    @GetMapping(path="/{userId}/matches")
    public List<Match> getUserMatches(@PathVariable int userId) {
        return userDao.getUserMatchScores(userId);
    }

    /**
     * Deletes user from the event per user request
     * @param userId, eventId
     */
    @DeleteMapping(path="/{userId}/events/{eventId}")
    public void removeUserFromEvent(@PathVariable int userId, @PathVariable int eventId){
        userDao.removeUserFromEvent(userId, eventId);
    }

    /**
     * sets or updates user's goal
     * @param user, userId
     */
    @PutMapping(path="/{userId}/goal")
    public User updateUserGoal(@PathVariable int userId, @RequestBody User user) {
        return userDao.updateUserGoal(user, userId);
    }

}
