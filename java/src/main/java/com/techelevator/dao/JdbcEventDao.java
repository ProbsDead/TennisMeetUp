package com.techelevator.dao;

import com.techelevator.model.Match;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Event;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Event> getAllEventsByGroupId(int groupId) {
        List<Event> groupEvents= new ArrayList<>();

        String sql = "SELECT e.event_id, e.event_name, e.description, e.start_time, " +
                "e.end_time, e.location, e.created_by FROM events e " +
                "JOIN groups_events ge ON e.event_id = ge.event_id " +
                "WHERE ge.group_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);

        while (rowSet.next()) {
            groupEvents.add(mapRowToEvent(rowSet));
        }
        return groupEvents;
    }

    public List<Event> getFutureEventsByGroupId(int groupId) {
        return null;
    }

    public void addNewUserEvent(Event newEvent) {

        String sql = "INSERT INTO events (event_name, description, start_time, " +
                        "end_time, location, created_by) " +
                        "VALUES (?,?,?,?,?,?);";

        jdbcTemplate.update(sql, newEvent.getEventName(), newEvent.getDescription(), newEvent.getStartTime(),
            newEvent.getEndTime(), newEvent.getLocation(), newEvent.getCreatedBy());

    }

    public Event getEventDetails(int eventId) {
        Event eventDetails = new Event();

        String sql = "SELECT * FROM events WHERE event_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, eventId);

        while(rowSet.next()) {
            eventDetails = mapRowToEvent(rowSet);
        }

        return eventDetails;
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
        event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
//        event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        event.setLocation(rs.getString("location"));
        event.setCreatedBy(rs.getInt("created_by"));

        return event;
    }

}
