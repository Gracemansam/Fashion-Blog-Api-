package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.response.CreatePostResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;
import com.graceman.fashionblogrestapi.response.SearchPostResponse;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto),CREATED);
    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> commentResponse (@PathVariable (value = "keyword") String keyword) {
        return new ResponseEntity<>(postService.searchPost(keyword),OK);

    }

}
