package com.techelevator.dao;

import com.techelevator.model.Request;

import java.util.List;

public interface RequestDao {
    void inviteNewMember(int groupId, int joiningUserId);

    List<Request> getAllCurrentRequests(int groupId);

    void approveOrDeclineRequest(Request request, int adminId);

    void sendRequestToJoinGroup(Request request);

}
