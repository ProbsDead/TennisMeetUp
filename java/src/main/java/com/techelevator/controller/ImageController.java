package com.techelevator.controller;

import com.techelevator.dao.JdbcGroupsDao;
import com.techelevator.dao.JdbcImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;

@CrossOrigin
@RestController
@RequestMapping("/image-upload")
public class ImageController {
    @Autowired
    private JdbcImageDao imageDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // receiving & storing the image file
    @PutMapping("/{groupId}")
    public ResponseEntity<String> uploadImage(@PathVariable int groupId, @RequestParam("image") MultipartFile file, HttpServletRequest request) {
        //save the image to the database using Spring JDBC
        try {
            //Extract relevant info from the file object

            // image in byte array
            byte[] imageBytes = file.getBytes();

            // content type field value (ex. image/png)
            // HttpServletRequest object : you can access various details of the incoming HTTP request
            String contentType = request.getParameter("contentType");
            imageDao.uploadImageToGroupTable(imageBytes, contentType, groupId);
            return ResponseEntity.ok("Image uploaded successfully!");

//            jdbcTemplate.update(connection -> {
//                PreparedStatement ps = connection
//                        .prepareStatement("UPDATE groups SET group_image=? WHERE group_id=?;");
//                ps.setBinaryStream(1, new ByteArrayInputStream(imageBytes), imageBytes.length);
//                ps.setInt(2, groupId);
//                return ps;
//            });

        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    @GetMapping("/retrieve/{groupId}")
    public MultipartFile retrieveImage(@PathVariable int groupId) {
        return null;
    }
}
