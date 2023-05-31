package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public void createNewGroup(Group newGroup) {
        String sql = "INSERT INTO groups (group_name, created_by, city, location, is_public, about) VALUES (?,?,?,?,?,?);";

        jdbcTemplate.update(sql, newGroup.getGroupName(), newGroup.getCreatedBy(), newGroup.getCity(), newGroup.getLocation(), newGroup.isPublic(), newGroup.getAbout());
    }

    //    /**
//     * This method retrieves the userId of the admin of a specific group before creating a new entity within
//     * the requests table inviting another user to join said group.
//     * @param groupId
//     * @param joiningUserId
//     */
//    @Override
//    public void inviteNewMember(int groupId, int joiningUserId) {
//
//        //retrieve admin Id
//        String sql = "SELECT user_id FROM groups_player WHERE group_id=? AND role=?;";
//        int adminId = jdbcTemplate.queryForObject(sql, int.class, groupId, "ROLE_ADMIN");
//
//        sql = "INSERT INTO requests(group_id, joining_user_id, admin_user_id, status, invite_or_request) VALUES(?,?,?,?,?);";
//        jdbcTemplate.update(sql, groupId, joiningUserId, adminId, false, "invite");
//
//    }

    // mapping for group
    private Group mapRowToGroup(SqlRowSet row) {
        Group group = new Group();

        group.setGroupId(row.getInt("group_id"));
        group.setGroupName(row.getString("group_name"));
        group.setCreatedBy(row.getInt("created_by"));
        group.setCity(row.getString("city"));
        group.setLocation(row.getString("location"));
        group.setPublic(row.getBoolean("is_public"));
        group.setAbout(row.getString("about"));

        return group;
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
