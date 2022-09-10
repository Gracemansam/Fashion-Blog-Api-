package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.response.CreatePostResponse;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
@RestController
@RequestMapping( value = "/api")
@AllArgsConstructor
public class PostController {

    private  final PostService postService;


    @PostMapping(value = "/createPost",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto),CREATED);
    }
}
