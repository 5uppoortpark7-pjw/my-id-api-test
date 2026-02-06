package com.example.myip.dto;

import com.example.myip.RequestType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class TaskIdRequest {
    @JsonProperty("request_type")
    private RequestType requestType;
    @JsonProperty("person_id")
    private String personId;
    @JsonProperty("external_id")
    private String externalId; // 클라이언트의 요청 고유 식별자
    private Settings settings;
    @JsonProperty("camera_image")
    private String cameraImage;
    @JsonProperty("id_image")
    private String idImage;

    public static TaskIdRequest of(String userId, MultipartFile cameraImage, MultipartFile idImage) {
        Settings settings = Settings.builder()
                .needBlurriness(true)
                .comparisonThreshold(0.5f)
                .singleFaceOnly(true)
                .colorImageOnly(true)
                .blinkThreshold(0.6f)
                .headRotation(true)
                .build();
        try {
            return TaskIdRequest.builder()
                    .requestType(RequestType.LIVENESS_COMPARISON)
                    //.personId(userId)
                    .cameraImage("data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(cameraImage.getBytes()))
                    .settings(settings)
                    //.externalId(userId) // 클라이언트의 요청 고유 식별자
                    .idImage("data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(idImage.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("이미지 파일 변환 실패");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Settings {
        @JsonProperty("need_blurriness")
        private Boolean needBlurriness;
        @JsonProperty("comparison_threshold")
        private Float comparisonThreshold;
        @JsonProperty("single_face_only")
        private Boolean singleFaceOnly;
        @JsonProperty("color_image_only")
        private Boolean colorImageOnly;
        @JsonProperty("blink_threshold")
        private Float blinkThreshold;
        @JsonProperty("head_rotation")
        private Boolean headRotation;
    }
}
