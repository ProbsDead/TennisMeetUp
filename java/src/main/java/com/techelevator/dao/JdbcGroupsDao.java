package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcGroupsDao implements GroupsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGroupsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Group getGroupInfo(int groupId) {
        Group group = null;

        String sql = "SELECT * FROM groups WHERE group_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);

        while(rowSet.next()) {
            group = mapRowToGroup(rowSet);
        }

        return group;
    }

    //TODO: write this once User logistics are figured out
    @Override
    public List<User> getGroupMembers(int groupId) {
        return null;
    }

    @Override
    public void inviteNewMember(int groupId, int joiningUserId) {

        //retrieve admin Id
        String sql = "SELECT user_id FROM groups_player WHERE group_id=? AND role=?;";
        int adminId = jdbcTemplate.queryForObject(sql, int.class, groupId, "ROLE_ADMIN");

        sql = "INSERT INTO requests(group_id, joining_user_id, admin_user_id, status, invite_or_request) VALUES(?,?,?,?,?);";
        jdbcTemplate.update(sql, groupId, joiningUserId, adminId, false, "invite");

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

        return group;
    }
}
