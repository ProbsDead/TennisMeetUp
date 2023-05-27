package com.techelevator.dao;

import com.techelevator.model.Request;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcRequestDao implements RequestDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

        /**
     * This method retrieves the userId of the admin of a specific group before creating a new entity within
     * the requests table inviting another user to join said group.
     * @param groupId
     * @param joiningUserId
     */
    @Override
    public void inviteNewMember(int groupId, int joiningUserId) {
        //retrieve admin Id
        String sql = "SELECT user_id FROM groups_player WHERE group_id=? AND role=?;";
        int adminId = jdbcTemplate.queryForObject(sql, int.class, groupId, "ROLE_ADMIN");

        sql = "INSERT INTO requests(group_id, joining_user_id, admin_user_id, status, invite_or_request) VALUES(?,?,?,?,?);";
        jdbcTemplate.update(sql, groupId, joiningUserId, adminId, false, "invite");
    }

    @Override
    public List<Request> getAllCurrentRequests(int groupId) {
        return null;
    }

    @Override
    public void approveOrDeclineRequest(Request request) {

    }
}
