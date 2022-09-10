package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.*;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.response.*;
import com.graceman.fashionblogrestapi.services.CommentService;
import com.graceman.fashionblogrestapi.services.LikeService;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor @Slf4j
@RequestMapping("/api")
public class UserController {

    private  UserService userService;



    @PostMapping (value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> register(@RequestBody UserRegisterDto userDto) {
        RegisterResponse registerResponse = userService.register(userDto);
        return new ResponseEntity<>(registerResponse,CREATED);
    }

    @PostMapping(value = "/login")
    //consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginDto loginDto){
        LoginResponse loginResponse =userService.login(loginDto);
        return new ResponseEntity<>(loginResponse,ACCEPTED);
    }
}
