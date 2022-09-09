package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.exception.PostNotFoundException;
import com.graceman.fashionblogrestapi.exception.UserNotFoundException;
import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.CommentRepository;
import com.graceman.fashionblogrestapi.repository.PostRepository;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.CommentResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;
import com.graceman.fashionblogrestapi.services.CommentService;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CommentImplementation(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.postService = postService;
    }



    @Override
    public CommentResponse comment(int user_id, int post_id, CommentDto commentDto) {
        User user = userService.findUserById(user_id);
        Post post = postService.findPostById(post_id);
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("success" , LocalDateTime.now() , comment , post);
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContaining(keyword);
        return new SearchCommentResponse("success" , LocalDateTime.now() , commentList);
    }
}
