package com.techelevator.dao;

import com.techelevator.model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcEventDaoTests extends BaseDaoTests {

    protected static final Event EVENT_1 = new Event(1, "event 1", "test event #1", LocalDateTime.parse("2023-06-09T10:30:00"), LocalDateTime.parse("2023-06-09T12:30:00"), "Huguenot Park", 3 );
    private JdbcEventDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcEventDao(jdbcTemplate);
    }
}
