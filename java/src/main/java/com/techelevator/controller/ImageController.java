package com.techelevator.controller;

import com.techelevator.dao.JdbcGroupsDao;
import com.techelevator.dao.JdbcImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/image-upload")
public class ImageController {
    @Autowired
    private JdbcImageDao imageDao;

    // receiving & storing the image file
    @PutMapping("/{groupId}")
    public ResponseEntity<String> uploadImage(@PathVariable int groupId, @RequestParam("image") MultipartFile file) {
        //save the image to the database using Spring JDBC
        try{
            //Extract relevant info from the file object
            byte[] imageBytes = file.getBytes();
            imageDao.uploadImageToGroupTable(imageBytes, groupId);
            return ResponseEntity.ok("Image uploaded successfully!");

        } catch(IOException e) {
            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }
}
