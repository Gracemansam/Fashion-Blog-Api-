package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
@AllArgsConstructor
@RestController
@RequestMapping(value="/api")
public class LikeController {

    private  final LikeService likeService;



    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<ApiResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody LikeDto likeDto) {
        return likeService.like(user_id, post_id, likeDto);
    }
}
