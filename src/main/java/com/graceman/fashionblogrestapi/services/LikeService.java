package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.response.LikeResponse;

public interface LikeService {
    LikeResponse like(int user_id, int post_id, LikeDto likeDto);
}
