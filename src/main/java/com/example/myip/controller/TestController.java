package com.example.myip.controller;

import com.example.myip.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {
    private final TestService testService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> test(@RequestParam MultipartFile cameraImage, @RequestParam MultipartFile idImage) {
        String accessToken = testService.getAccessToken();
        String taskId = testService.compareImages(accessToken, cameraImage, idImage);
        return new ResponseEntity<>(testService.getUserData(accessToken, taskId), HttpStatus.OK);
    }
}
