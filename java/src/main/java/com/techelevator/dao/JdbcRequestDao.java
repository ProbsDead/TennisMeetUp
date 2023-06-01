package com.techelevator.dao;

import com.techelevator.model.Request;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
        List<Request> pendingRequests = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE status = 'Pending' AND group_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, groupId);
        while(row.next()){
            pendingRequests.add(mapRowToRequest(row));
        }
        return pendingRequests;
    }

    @Override
    public void approveOrDeclineRequest(Request request) {

    }

    @Override
    public void sendRequestToJoinGroup(Request request) {

    }

    private Request mapRowToRequest(SqlRowSet row){
        Request request = new Request();

        request.setRequestId(row.getInt("request_id"));
        request.setGroupId(row.getInt("group_id"));
        request.setJoiningUserId(row.getInt("joining_user_id"));
        request.setAdminUserId(row.getInt("admin_user_id"));
        request.setStatus(row.getString("status"));
        request.setInviteOrRequest(row.getString("invite_or_request"));

        return request;
    }
}
