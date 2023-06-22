package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcGroupsDao implements GroupsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGroupsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method selects all the information from the Groups table for a specific group using that groups
     * groupID.  It maps that information into a Group object and returns it.
     * @param groupId
     * @return group object
     */
    @Override
    public Group getGroupInfo(int groupId) {
        Group group = new Group();

        String sql = "SELECT * FROM groups WHERE group_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);

        while(rowSet.next()) {
            group = mapRowToGroup(rowSet);
        }

        return group;
    }

    /**
     * This method returns a list of User Objects that belong to a specific group
     * @param groupId
     * @return list of User objects
     */
    @Override
    public List<User> getGroupMembers(int groupId) {
        List<User> groupMembersList = new ArrayList<>();


        String sql = "SELECT gp.user_id, first_name, last_name, username, email, state, city, gp.role " +
                "FROM users " +
                "JOIN groups_player gp ON users.user_id = gp.user_id " +
                "WHERE group_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, groupId);

        while (row.next()){
            groupMembersList.add(mapRowToUser(row));
        }
        return groupMembersList;
    }

    /**
     * This method returns a list of all groups.
     * @return list of Group objects
     * */

    @Override
    public List<Group> getAllGroups() {
        List<Group> allGroups = new ArrayList<>();

        String sql = "SELECT * FROM groups;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            allGroups.add(mapRowToGroup(rowSet));
        }
        return allGroups;
    }

    /**
     * Create a new group from a received Group Object.  Currently, returns nothing but has been tested in Postman
     * @param newGroup
     */
    @Override
    public Group createNewGroup(Group newGroup) {
        String sql = "INSERT INTO groups (group_name, created_by, city, state, location, is_public, about) VALUES (?,?,?,?,?,?,?) RETURNING group_id;";

        int groupId = jdbcTemplate.queryForObject(sql, int.class, newGroup.getGroupName(), newGroup.getCreatedBy(), newGroup.getCity(), newGroup.getState(),newGroup.getLocation(), newGroup.isPublic(), newGroup.getAbout());
        return getGroupInfo(groupId);
    }

    @Override
    public List<Group> getAllPublicGroups() {
        List<Group> publicGroups = new ArrayList<>();

        String sql = "SELECT * FROM groups WHERE is_public = true;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        while(row.next()){
            publicGroups.add(mapRowToGroup(row));
        }

        return publicGroups;
    }

    @Override
    public List<Group> getUsersGroups(int userId) {
        List<Group> usersGroups = new ArrayList<>();

        String sql =
//                "SELECT groups.group_id, group_name, city, location, created_by, is_public, about " +
                "SELECT * FROM groups " +
                "JOIN groups_player gp ON gp.group_id = groups.group_id " +
                "WHERE user_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);
        while(row.next()){
            usersGroups.add(mapRowToGroup(row));
        }

        return usersGroups;
    }


    // mapping for group
    private Group mapRowToGroup(SqlRowSet row) {
        Group group = new Group();

        group.setGroupId(row.getInt("group_id"));
        group.setGroupName(row.getString("group_name"));
        group.setCreatedBy(row.getInt("created_by"));
        group.setCity(row.getString("city"));
        group.setLocation(row.getString("location"));
        group.setPublic(row.getBoolean("is_public"));
        group.setState(row.getString("state"));
        group.setAbout(row.getString("about"));
        group.setGroupImage(getImageData(row.getInt("group_id")));
        group.setImageType(row.getString("image_type"));

        return group;
    }

    private byte[] getImageData(int groupId) {
        String sql= "SELECT group_image FROM groups WHERE group_id=?;";
        return jdbcTemplate.query(sql, new Object[]{groupId}, rs -> {
            if (rs.next()) {
                return rs.getBytes(1);
            }
            return null;
        });
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
//        user.setPassword(rs.getString("password_hash"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setCity(rs.getString("city"));
        user.setEmail(rs.getString("email"));
        user.setState(rs.getString("state"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}
