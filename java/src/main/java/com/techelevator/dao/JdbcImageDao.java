package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcImageDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void uploadImageToGroupTable(byte[] imageBytes, int groupId) {
        // use Spring JDBC to insert the image into the database;
        String sql = "UPDATE groups SET group_image=? WHERE group_id=?;";
        jdbcTemplate.update(sql,imageBytes, groupId);

    }
}
