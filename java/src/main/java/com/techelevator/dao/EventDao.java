package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Match;

import java.util.List;

public interface EventDao {
    List<Event> getAllEventsByGroupId(int groupId);

    List<Event> getFutureEventsByGroupId(int groupId);

    void addNewUserEvent(Event newEvent);

    Event getEventDetails(int eventId);

    void updateEventDetails(int creatorId, int eventId);

    void deleteEvent(int creatorId, int eventId);

    void joinEvent(int userId, int eventId);

    List<Match> getMatchesByEventId(int eventId);

}
