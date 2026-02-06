package com.example.myip.service;

import com.example.myip.util.HttpUtil;
import com.example.myip.dto.AccessTokenResponse;
import com.example.myip.dto.TaskIdRequest;
import com.example.myip.dto.UserDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TestService {
    private static final String BASE_URL = "https://api.myid.vision";
    @Value("${my-id.username}")
    private String USRENAME;
    @Value("${my-id.password}")
    private String PASSWORD;

    public String getAccessToken() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", USRENAME);
        params.put("password", PASSWORD);

        AccessTokenResponse response = HttpUtil.builder()
                .url(BASE_URL+"/api/token")
                .body(params)
                .post(AccessTokenResponse.class);

        return response.getAccess();
    }

    public String compareImages(String accessToken, MultipartFile cameraImage, MultipartFile idImage) {
        TaskIdRequest body = TaskIdRequest.of("234", cameraImage, idImage);
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer "+accessToken);

        UserDataResponse.TaskId response = HttpUtil.builder()
                .url(BASE_URL+"/api/verification")
                .httpHeaders(header)
                .body(body)
                .post(UserDataResponse.TaskId.class);

        return response.getTaskId();
    }

    public Object getUserData(String accessToken, String taskId) {
        String url = BASE_URL+"/api/verification/"+taskId+"?wait=3";
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer "+accessToken);

        return HttpUtil.builder()
                .url(url)
                .httpHeaders(header)
                .get(UserDataResponse.Success.class);
    }

}
