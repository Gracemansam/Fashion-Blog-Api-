package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( value = "/api")
@AllArgsConstructor
public class PostController {

    private  final PostService postService;


    @PostMapping(value = "/createPost",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> create(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<ApiResponse> commentResponse (@PathVariable (value = "keyword") String keyword) {
        return postService.searchPost(keyword);
    }

}
