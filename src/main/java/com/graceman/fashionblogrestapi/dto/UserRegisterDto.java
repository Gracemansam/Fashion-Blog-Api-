package com.graceman.fashionblogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterDto {
    private String name;
    private String email;
    private String role;
    private String password;
}
