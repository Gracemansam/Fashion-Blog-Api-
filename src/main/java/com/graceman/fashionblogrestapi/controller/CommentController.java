package com.graceman.fashionblogrestapi.controller;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.response.CommentResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;
import com.graceman.fashionblogrestapi.response.SearchPostResponse;
import com.graceman.fashionblogrestapi.services.CommentService;
import com.graceman.fashionblogrestapi.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.comment(user_id,post_id,commentDto),CREATED);
    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> commentResponse (@PathVariable (value = "keyword") String keyword) {
        return new ResponseEntity<>(commentService.searchComment(keyword),OK);

    }


}
