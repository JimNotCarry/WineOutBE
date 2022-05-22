package com.WineOutBE.api;

import com.WineOutBE.security.AWS;
import com.WineOutBE.service.UserService;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class Images {

    private final UserService userService;

    private final AWS aws = new AWS();


    @PostMapping(path = "/postImages")
    public void uploadHeaderImage(Authentication auth, @RequestParam("occasiondate") String occasiondate, @RequestPart("file") MultipartFile file) throws IOException {

        Map<String, String> metadata = new HashMap<>();

        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        ObjectMetadata objectMetadata = new ObjectMetadata();

        metadata.forEach(objectMetadata::addUserMetadata);

        try {
            aws.getS3client().putObject("wineout","Pictures/" + auth.getName() + "/" + occasiondate + "/header.jpg", file.getInputStream(), objectMetadata);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/getFile")
    public ResponseEntity<String> getFile(@RequestParam(name = "occasionDate") String occasionDate, Authentication auth) throws IOException {

        String keyname = "Pictures/" + auth.getName() + "/" + occasionDate + "/header.jpg";

        S3Object object = aws.getS3client().getObject("wineout", keyname);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
                .body(Base64.getEncoder().encodeToString(object.getObjectContent().readAllBytes()));
    }

}
