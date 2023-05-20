package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Match {
    @JsonProperty("match_id")
    private int matchId;

    @JsonProperty("event_id")
    private int eventId;

    private String score;

    private int winner;

    @JsonProperty("winner_two")
    private int winnerTwo;

    @JsonProperty("match_length")
    private int matchLength;

    public Match() {}

    public int getMatchId() {
        return matchId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getScore() {
        return score;
    }

    public int getWinner() {
        return winner;
    }

    public int getWinnerTwo() {
        return winnerTwo;
    }

    public int getMatchLength() {
        return matchLength;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setWinnerTwo(int winnerTwo) {
        this.winnerTwo = winnerTwo;
    }

    public void setMatchLength(int matchLength) {
        this.matchLength = matchLength;
    }
}
