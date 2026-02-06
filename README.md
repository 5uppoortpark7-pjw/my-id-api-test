# MyID API Test

## 기술 스택
* Spring Boot 4.0.2
* Java17

## 환경 변수 설정 (Application.yaml)
```
spring:
  application:
    name: myip
my-id:
  username: {USERNAME}
  password: {PASSWORD}
```

## 프로젝트 구조
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

## 로컬 실행 시 API 테스트 엔드포인트, CURL
[POST] http://localhost:8080/api/v1/test
```
curl --location 'http://localhost:8080/api/v1/test' \
--form 'idImage=@"/Users/.../Downloads/images.jpeg"' \
--form 'cameraImage=@"/Users/.../Downloads/images(1).jpeg"'
```
