package com.techelevator.model;

public class Request {
    private int requestId;
    private int groupId;
    private int joiningUserId;
    private int adminUserId;
    private String status;
    private String inviteOrRequest;

    public Request(){}

    public Request(int requestId, int groupId, int joiningUserId, int adminUserId, String status, String inviteOrRequest) {
        this.requestId = requestId;
        this.groupId = groupId;
        this.joiningUserId = joiningUserId;
        this.adminUserId = adminUserId;
        this.status = status;
        this.inviteOrRequest = inviteOrRequest;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getJoiningUserId() {
        return joiningUserId;
    }

    public void setJoiningUserId(int joiningUserId) {
        this.joiningUserId = joiningUserId;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInviteOrRequest() {
        return inviteOrRequest;
    }

    public void setInviteOrRequest(String inviteOrRequest) {
        this.inviteOrRequest = inviteOrRequest;
    }
}
