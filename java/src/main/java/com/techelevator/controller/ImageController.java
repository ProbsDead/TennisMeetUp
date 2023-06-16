package com.techelevator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/image-upload")
public class ImageController {
//    @PostMapping("/{groupId}")
//    public ResponseEntity<String> uploadImage(@PathVariable int groupId, @RequestParam("image") MultipartFile file) {
//        //save the image to the database using Spring JDBC
//        try{
//            //Extract relevant info from the file object
//            byte[] imageBytes = file.getBytes();
//
//            // use Spring JDBC to insert the image into the database;
//            String sql = "UPDATE"
//
//        }
//    }
}
