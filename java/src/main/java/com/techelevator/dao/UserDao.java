package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Match;
import com.techelevator.model.User;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String firstName, String lastName, String email, String state, String city, String role);

    List<Event> getAllUserEvents(int userId);

    List<Match> getUserMatchScores(int userId);

    List<Event> getUpcomingUserEvents(int userId);

    void removeUserFromEvent(int userId, int eventId);

    void setUserGoal(User user);

    void updateUserGoal(User user);

}
