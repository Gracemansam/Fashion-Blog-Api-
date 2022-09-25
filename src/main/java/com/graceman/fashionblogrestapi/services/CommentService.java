package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<ApiResponse> comment(int user_id, int post_id, CommentDto commentDto);

    ResponseEntity<ApiResponse> searchComment(String keyword);
}
