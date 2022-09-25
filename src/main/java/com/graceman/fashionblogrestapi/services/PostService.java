package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<ApiResponse> createPost(PostDto postDto);

    String makeSlug(String input);



    ResponseEntity<ApiResponse> searchPost(String keyword);

    Post findPostById(int id);
}
