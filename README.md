# MyID API Test

### 기술 스택
* Spring Boot 4.0.2
* Java17

### Application.yaml
```
spring:
  application:
    name: myip
my-id:
  username: {USERNAME}
  password: {PASSWORD}
```

### 프로젝트 구조
```
main.java.com.example.myip
├─controller/
│  └─TestController.java
├─dto/
│  ├─AccessTokenResponse.java
│  ├─RequestType.java
│  ├─TaskIdRequest.java
│  └─UserDataResponse.java
├─service/
│  └─TestService.java
└─util/
   └─HttpUtil.java
```
