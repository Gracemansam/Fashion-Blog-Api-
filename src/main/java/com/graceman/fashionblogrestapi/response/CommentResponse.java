package com.graceman.fashionblogrestapi.response;

import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;
}
