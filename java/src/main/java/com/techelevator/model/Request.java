package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("request_id")
    private int request_id;
    @JsonProperty("group_id")
    private int group_id;
    @JsonProperty("joining_user_id")
    private int joiningUserId;
    @JsonProperty("admin_user_id")
    private int adminUserId;
    private boolean status;
    @JsonProperty("invite_or_request")
    private String inviteOrRequest;

    public int getRequest_id() {
        return request_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public int getJoiningUserId() {
        return joiningUserId;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getInviteOrRequest() {
        return inviteOrRequest;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setJoiningUserId(int joiningUserId) {
        this.joiningUserId = joiningUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInviteOrRequest(String inviteOrRequest) {
        this.inviteOrRequest = inviteOrRequest;
    }
}
