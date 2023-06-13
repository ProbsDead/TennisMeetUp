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
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events e " +
                "JOIN groups_events ge ON e.event_id = ge.event_id " +
                "WHERE ge.group_id = ? AND e.start_time > NOW();";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);

        while(rowSet.next()) {
            events.add(mapRowToEvent(rowSet));
        }
        return events;

    }

    public Event addNewEvent(Event newEvent, int groupId) {

        String eventSql = "INSERT INTO events (event_name, description, start_time, " +
                        "end_time, location, created_by) " +
                        "VALUES (?,?,?,?,?,?) RETURNING event_id;";

        /* Kate: the query is fine, but Jdbc's update method actually returns the number of
            rows returned, not the newly created serial id. You would need to use .queryForObject method
         */
        // this would return the event_id for the newly created event.  It is successfully doing this part.
//            return jdbcTemplate.update(eventSql, newEvent.getEventName(), newEvent.getDescription(), newEvent.getStartTime(),
//                    newEvent.getEndTime(), newEvent.getLocation(), newEvent.getCreatedBy());
        //
        int eventId = jdbcTemplate.queryForObject(eventSql, int.class, newEvent.getEventName(), newEvent.getDescription(), newEvent.getStartTime(),
                newEvent.getEndTime(), newEvent.getLocation(), newEvent.getCreatedBy());

        return getEventDetails(eventId);
    }

    public void addToGroupsEvents(int groupId, int newEventId){
        String groupEventSql = "INSERT INTO groups_events (group_id, event_id) " +
                                "VALUES (?,?); ";
        jdbcTemplate.update(groupEventSql, groupId, newEventId);
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

    /**
     * This method receives a (updated) Event object from frontend, sets/updates the User goal and returns the updated User object
        NOTE: make sure to check from frontend before sending the request
              whether the person making this request is the one who created the event in the first place.
              Only the user (group member) that created/added a new event should be able to modify/delete that event
              so no one else messes with it
     */
    public Event updateEventDetails(Event event, int eventId) {

        String sql = "UPDATE events SET event_name=?, description=?, start_time=?, " +
                    "end_time=?, location=? WHERE event_id=?;";

        jdbcTemplate.update(sql, event.getEventName(), event.getDescription(), event.getStartTime(),
                            event.getEndTime(), event.getLocation(), eventId);

        return getEventDetails(eventId);

    }

    public void deleteEvent(int creatorId, int eventId) {
        // why do we need the creatorId here?
    }

    public void joinEvent(int userId, int eventId) {

    }

    public List<Match> getMatchesByEventId(int eventId) {
        List <Match> matchList = new ArrayList<>();



        return null;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();

        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setDescription(rs.getString("description"));
        event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        event.setLocation(rs.getString("location"));
        event.setCreatedBy(rs.getInt("created_by"));


        return event;
    }

}
