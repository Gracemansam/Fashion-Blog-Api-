package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.response.CreatePostResponse;
import com.graceman.fashionblogrestapi.response.SearchPostResponse;

public interface PostService {
    CreatePostResponse createPost(PostDto postDto);

    String makeSlug(String input);



    SearchPostResponse searchPost(String keyword);

    Post findPostById(int id);
}
