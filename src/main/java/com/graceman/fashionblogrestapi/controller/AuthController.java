package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.AuthRequest;
import com.graceman.fashionblogrestapi.dto.GoogleOauth2;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    @PostMapping("/api/auth")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody AuthRequest request){

        return  userService.authenticate(request);
    }
    @PostMapping("/api/oauth2/callback")
    public ResponseEntity<ApiResponse> authenticateOauth2(@RequestBody GoogleOauth2 principal){
        return  userService.authenticateOauth2(principal);
    }
}
