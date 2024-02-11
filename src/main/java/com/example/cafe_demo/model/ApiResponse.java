package com.example.cafe_demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Meta meta;
    private Object data;

    public ApiResponse(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    // getters and setters

    public static class Meta {
        private int code;
        private String message;

        public Meta(int code, String message) {
            this.code = code;
            this.message = message;
        }

        // getters and setters
    }
}
