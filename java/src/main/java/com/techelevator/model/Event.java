package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class Event {
    @JsonProperty("event_id")
    private int eventId;

    @JsonProperty("event_name")
    private String eventName;

    private String description;
    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;
    private String location;
    @JsonProperty("created_by")
    private int createdBy;

    public Event() {}
    public Event(int eventId, String eventName, String description, LocalDateTime startTime,
                 LocalDateTime endTime, String location, int createdBy) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.createdBy = createdBy;
    }

    public int getEventId() {
        return eventId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getEventName() {
        return eventName;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
