package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.response.CommentResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;

public interface CommentService {
    CommentResponse comment(int user_id, int post_id, CommentDto commentDto);

    SearchCommentResponse searchComment(String keyword);
}
