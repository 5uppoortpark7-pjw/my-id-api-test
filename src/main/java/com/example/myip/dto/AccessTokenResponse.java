package com.example.myip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenResponse {
    // AccessToken 24시간 유효
    private String access;
    // RefreshToken 30일 유효
    private String refresh;
}
