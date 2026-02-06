package com.example.myip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class UserDataResponse {
    @Getter
    @Setter
    public static class TaskId {
        @JsonProperty("task_id")
        private String taskId;
    }
    @Getter
    @Setter
    @ToString
    public static class Success {
        @JsonProperty("task_id")
        private String taskId;
        private State state;
        private Data data;
    }

    @Getter
    @Setter
    @ToString
    public static class Error {
        @JsonProperty("task_id")
        private String taskId;
        private State state;
    }

    @Getter
    @Setter
    @ToString
    public static class State {
        private int code;
        private String message;
    }

    @Getter
    @Setter
    @ToString
    public static class Data {
        @JsonProperty("is_recognized")
        private Boolean isRecognized;
        @JsonProperty("person_id")
        private String personId;
        @JsonProperty("external_id")
        private Integer externalId;
        @JsonProperty("is_alive")
        private Boolean isAlive;
        private Details details;
    }

    @Getter
    @Setter
    @ToString
    public static class Details {
        @JsonProperty("liveness_blurriness")
        private Double livenessBlurriness;
        @JsonProperty("comparison_value")
        private Double comparisonValue;
    }
}
