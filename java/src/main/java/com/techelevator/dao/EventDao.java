package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Match;
import com.techelevator.model.User;

import java.util.List;

public interface EventDao {
    List<Event> getAllEventsByGroupId(int groupId);

    List<Event> getFutureEventsByGroupId(int groupId);

    Event addNewEvent(Event newEvent, int groupId);

    void addToGroupsEvents(int groupId, int newEventId);

    Event getEventDetails(int eventId);

    Event updateEventDetails(Event event, int eventId);

    void deleteEvent(int eventId);

    void joinEvent(int userId, int eventId);

    List<Match> getMatchesByEventId(int eventId);

    List<User> getPlayersByEventId(int eventId);

    Match createNewMatchForEvent(int eventId, Match match);

}
