package com.techelevator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JdbcImageDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void uploadImageToGroupTable(byte[] imageBytes, String imageType, int groupId) {
        // use Spring JDBC to insert the image into the database;
        String sql = "UPDATE groups SET group_image=?, image_type=? WHERE group_id=?;";

        jdbcTemplate.update(sql,imageBytes, imageType, groupId);

    }

//    public void uploadImage(byte[] imageBytes, int groupId) {
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement("UPDATE groups SET group_image=? WHERE group_id=?");
//            ps.setBinaryStream(1, new ByteArrayInputStream(imageBytes), imageBytes.length);
//            ps.setInt(2, groupId);
//            return ps;
//        });
//
//        }
}
