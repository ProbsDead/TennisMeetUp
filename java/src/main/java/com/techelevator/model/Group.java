package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

public class Group {
    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("created_by")
    private int createdBy;

    private String city;
    private String location;
    @JsonProperty("is_public")
    private boolean isPublic;

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
