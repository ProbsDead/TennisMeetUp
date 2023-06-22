package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.Event;
import com.techelevator.model.Match;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String firstName, String lastName, String email, String state, String city, String role) {
        String insertUserSql = "insert into users (username, password_hash, first_name, last_name, email, state, city, role) values (?,?,?,?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash,firstName, lastName, email, state, city, ssRole) == 1;
    }

    /**
     * This method returns a list of Event objects that a user is part of
     * @param userId
     * @return list of Event objects that a user is signed up to
     */
    @Override
    public List<Event> getAllUserEvents(int userId) {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM events " +
                "JOIN user_event ue ON ue.event_id = events.event_id " +
                "WHERE user_id=?;";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);

        while(rs.next()) {
            events.add(mapRowToEvent(rs));
        }
        return events;
    }

    /**
     * This method returns only upcoming (future) events that user signed up for
     * @param userId
     * @return a list of Event objects with date/time in the future
     */
    @Override
    public List<Event> getUpcomingUserEvents(int userId) {
        List<Event> events = new ArrayList<>();
       String sql = "SELECT * FROM events " +
               "JOIN user_event ue ON ue.event_id = events.event_id " +
               "WHERE user_id=? AND events.start_time > NOW();";

       SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

       while(rowSet.next()) {
           events.add(mapRowToEvent(rowSet));
       }
       return events;
    }

    /**
     * This method returns a list of Match objects that User participated in.
     * Once frontend receives this list, display match history by calculating stats and listing scores for each match
     * @param userId
     * @return
     */
    @Override
    public List<Match> getUserMatchScores(int userId) {
        List<Match> matches = new ArrayList<>();

        String sql = "SELECT * FROM match " +
                "JOIN match_user mu ON mu.match_id = match.match_id " +
                "JOIN events ON events.event_id = match.event_id " +
                "WHERE user_id=? ORDER BY events.start_time ASC;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()) {
            matches.add(mapRowToMatch(rowSet));
        }
        return matches;
    }


    @Override
    public void removeUserFromEvent(int userId, int eventId) {
        String sql="DELETE FROM user_event WHERE user_id=? AND event_id=?;";

        jdbcTemplate.update(sql,userId,eventId);
    }

    /**
     * This method receives a User object from frontend, sets/updates the User goal and returns the updated User object
     * @param user
     * @return updated User object
     */
    @Override
    public User updateUserGoal(User user, int userId) {
        String sql="UPDATE users SET goal=? WHERE user_id =?;";
        jdbcTemplate.update(sql, user.getGoal(), userId);

        return getUserById(userId);
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setCity(rs.getString("city"));
        user.setEmail(rs.getString("email"));
        user.setState(rs.getString("state"));
        user.setRole(rs.getString("role"));
        user.setGoal(rs.getString("goal"));

        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
    private Event mapRowToEvent(SqlRowSet row) {
        Event event = new Event();

        event.setEventId(row.getInt("event_id"));
        event.setEventName(row.getString("event_name"));
        event.setDescription(row.getString("description"));
        event.setStartTime(row.getTimestamp( "start_time").toLocalDateTime());
        event.setEndTime(row.getTimestamp("end_time").toLocalDateTime());
        event.setCreatedBy(row.getInt("created_by"));

        return event;
    }

    private Match mapRowToMatch(SqlRowSet row) {
        Match match = new Match();

        match.setMatchId(row.getInt("match_id"));
        match.setEventId(row.getInt("event_id"));
        match.setScore(row.getString("score"));
        match.setWinner(row.getInt("winner"));
        match.setWinnerTwo(row.getInt("winner_two"));
        match.setMatchLength(row.getInt("match_length"));

        return match;
    }
}
