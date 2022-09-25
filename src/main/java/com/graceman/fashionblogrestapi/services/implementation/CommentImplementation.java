package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.CommentRepository;
import com.graceman.fashionblogrestapi.repository.PostRepository;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.CommentService;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import com.graceman.fashionblogrestapi.utils.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentImplementation implements CommentService {
    private CommentRepository commentRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

    private UserService userService;
    private PostService postService;
    private Responder responder;

    @Autowired
    public CommentImplementation(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, UserService userService, PostService postService,Responder responder) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.postService = postService;
        this.responder = responder;
    }



    @Override
    public ResponseEntity<ApiResponse> comment(int user_id, int post_id, CommentDto commentDto) {
        User user = userService.findUserById(user_id);
        Post post = postService.findPostById(post_id);
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return responder.success("Comment created successfully", comment);
    }

    @Override
    public ResponseEntity<ApiResponse> searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContaining(keyword);
        return responder.success("Comment found", commentList);
    }
}
