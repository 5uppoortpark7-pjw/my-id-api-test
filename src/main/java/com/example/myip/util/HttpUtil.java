package com.example.myip.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpUtil {

    public static RestTemplateBuilder builder() {
        return new RestTemplateBuilder();
    }

    public static class RestTemplateBuilder {
        private final RestTemplate restTemplate = new RestTemplate();
        private String url;
        private Map<String, Object> params;
        private Object body;
        private HttpHeaders headers;

        public RestTemplateBuilder httpHeaders(HttpHeaders headers) {
            this.headers = headers;
            return this;
        }

        public RestTemplateBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RestTemplateBuilder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public <T> RestTemplateBuilder body(T body) {
            this.body = body;
            return this;
        }

        public <T> T get(Class<T> clazz) {
            StringBuilder queryParam = new StringBuilder();
            if (params != null && !params.isEmpty()) {
                for(String key : params.keySet()) {
                    queryParam.append(key).append("=").append(params.get(key)).append("&");
                }
            }
            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<T> response = restTemplate.exchange(url + queryParam, HttpMethod.GET, httpEntity, clazz);

            if(!response.getStatusCode().is2xxSuccessful())
                throw new RuntimeException("get api 실패!~");

            return response.getBody();
        }

        public <T> T post(Class<T> clazz) {
            HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, clazz);

            if(!response.getStatusCode().is2xxSuccessful())
                throw new RuntimeException("post api 실패!~");

            return response.getBody();
        }

    }
}
