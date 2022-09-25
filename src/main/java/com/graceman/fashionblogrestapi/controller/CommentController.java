package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@AllArgsConstructor
@RestController
@RequestMapping(value="/api")
public class CommentController {

    private final CommentService commentService;



    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public ResponseEntity<ApiResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody CommentDto commentDto) {
        return commentService.comment(user_id, post_id, commentDto);
    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<ApiResponse> commentResponse (@PathVariable (value = "keyword") String keyword) {
        return commentService.searchComment(keyword);

    }


}
