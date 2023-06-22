package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.web.multipart.MultipartFile;

public class Group {
    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("created_by")
    private int createdBy;
    private String city;
    private String state;
    private String location;
    @JsonProperty("is_public")
    private boolean isPublic;
    private String about;

    @JsonProperty("group_image")
    private byte[] groupImage;

    @JsonProperty("image_type")
    private String imageType;

    public Group(int groupId, String groupName, int createdBy, String city, String state, String location, boolean isPublic, String about) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createdBy = createdBy;
        this.city = city;
        this.state = state;
        this.location = location;
        this.isPublic = isPublic;
        this.about = about;
    }
    public Group(){}

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(byte[] groupImage) {
        this.groupImage = groupImage;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
