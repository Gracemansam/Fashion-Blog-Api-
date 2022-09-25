package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface LikeService {
    ResponseEntity<ApiResponse> like(int user_id, int post_id, LikeDto likeDto);
}
