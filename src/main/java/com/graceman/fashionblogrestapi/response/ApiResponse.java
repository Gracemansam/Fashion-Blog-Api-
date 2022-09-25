package com.graceman.fashionblogrestapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private LocalDateTime timeStamp;
    private T payload;
}
