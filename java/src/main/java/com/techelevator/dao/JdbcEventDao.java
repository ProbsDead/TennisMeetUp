package com.techelevator.dao;

import com.techelevator.model.Match;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Event;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcEventDao implements EventDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Event> getAllEventsByGroupId(int groupId) {
        List<Event> groupEvents= new ArrayList<>();

        String sql = "SELECT e.event_id, e.event_name, e.description, e.start_time, " +
                "e.location, e.created_by FROM events e " +
                "JOIN groups_events ge on e.event_id = ge.event_id " +
                "JOIN groups g on ge.group_id = g.group_id " +
                "where g.group_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);

        while (rowSet.next()) {
            groupEvents.add(mapRowToEvent(rowSet));
        }
        return groupEvents;
    }

    public List<Event> getFutureEventsByGroupId(int groupId) {
        return null;
    }

    public Event addNewEvent(int groupId, int userId) {
        return null;
    }

    public Event getEventDetails(int eventId) {
        return null;
    }

    public void updateEventDetails(int creatorId, int eventId) {

    }

    public void deleteEvent(int creatorId, int eventId) {

    }

    public void joinEvent(int userId, int eventId) {

    }

    public List<Match> getMatchesByEventId(int eventId) {
        return null;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();

        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setDescription(rs.getString("description"));
        event.setStartTime(rs.getTimestamp("start_time"));
        event.setLocation(rs.getString("location"));
        event.setCreatedBy(rs.getInt("created_by"));

        return event;
    }

}
