package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.exception.PostAlreadyLikedException;
import com.graceman.fashionblogrestapi.model.Like;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.LikeRepository;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.services.LikeService;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import com.graceman.fashionblogrestapi.utils.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class LikeImplementation implements LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;
    private Responder responder;


    @Autowired
    public LikeImplementation(LikeRepository likeRepository, UserService userService, PostService postService,Responder responder) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
        this.responder = responder;
    }


   @Override
    public ResponseEntity<ApiResponse> like(int user_id, int post_id, LikeDto likeDto) {
        Like like = new Like();
        User user = userService.findUserById(user_id);
        Post post = postService.findPostById(post_id);
       ResponseEntity<ApiResponse>  likeResponse = null;
        Like duplicateLike = likeRepository.findLikeByUserIdAndPostId(user_id, post_id);
        if (duplicateLike == null) {
            like.setLiked(likeDto.isLiked());
            like.setUser(user);
            like.setPost(post);

            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(post_id);
            likeResponse = responder.success("Post liked successfully", likeList);
        } else {
            likeRepository.delete(duplicateLike);
           // likeResponse = new LikeResponse("", LocalDateTime.now(), like, likeList.size());
            throw new PostAlreadyLikedException("This post has been liked, It is now Unliked :(");
        }
         return likeResponse;
    }
}
