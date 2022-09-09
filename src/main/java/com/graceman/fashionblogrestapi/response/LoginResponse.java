package com.graceman.fashionblogrestapi.response;

import com.graceman.fashionblogrestapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
