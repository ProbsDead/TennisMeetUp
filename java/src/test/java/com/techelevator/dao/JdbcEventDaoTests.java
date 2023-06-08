package com.techelevator.dao;

import com.techelevator.model.Event;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcEventDaoTests extends BaseDaoTests {

    private JdbcEventDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcEventDao(jdbcTemplate);
    }
}
