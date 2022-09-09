package com.graceman.fashionblogrestapi.services.implementation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.exception.PostAlreadyLikedException;
import com.graceman.fashionblogrestapi.model.Like;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.LikeRepository;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LikeImplementation.class})
@ExtendWith(SpringExtension.class)
class LikeImplementationTest {
    @Autowired
    private LikeImplementation likeImplementation;

    @MockBean
    private LikeRepository likeRepository;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;


    @Test
    void testLike() {
        User user = new User();
        user.setCommentList(new ArrayList<>());
        user.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLikeList(new HashSet<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPostList(new ArrayList<>());
        user.setRole("Role");
        user.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Post post = new Post();
        post.setCommentList(new ArrayList<>());
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDescription("The characteristics of someone or something");
        post.setFeaturedImage("Featured Image");
        post.setId(1);
        post.setLikeList(new ArrayList<>());
        post.setSlug("Slug");
        post.setTitle("Dr");
        post.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setUser(user);

        User user1 = new User();
        user1.setCommentList(new ArrayList<>());
        user1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setLikeList(new HashSet<>());
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPostList(new ArrayList<>());
        user1.setRole("Role");
        user1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Like like = new Like();
        like.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like.setId(1);
        like.setLiked(true);
        like.setPost(post);
        like.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like.setUser(user1);

        User user2 = new User();
        user2.setCommentList(new ArrayList<>());
        user2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setLikeList(new HashSet<>());
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPostList(new ArrayList<>());
        user2.setRole("Role");
        user2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Post post1 = new Post();
        post1.setCommentList(new ArrayList<>());
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setDescription("The characteristics of someone or something");
        post1.setFeaturedImage("Featured Image");
        post1.setId(1);
        post1.setLikeList(new ArrayList<>());
        post1.setSlug("Slug");
        post1.setTitle("Dr");
        post1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setUser(user2);

        User user3 = new User();
        user3.setCommentList(new ArrayList<>());
        user3.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setLikeList(new HashSet<>());
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPostList(new ArrayList<>());
        user3.setRole("Role");
        user3.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Like like1 = new Like();
        like1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like1.setId(1);
        like1.setLiked(true);
        like1.setPost(post1);
        like1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like1.setUser(user3);
        doNothing().when(likeRepository).delete((Like) any());
        when(likeRepository.findLikeByUserIdAndPostId(anyInt(), anyInt())).thenReturn(like);
        when(likeRepository.save((Like) any())).thenReturn(like1);
        when(likeRepository.likeList(anyInt())).thenReturn(new ArrayList<>());

        User user4 = new User();
        user4.setCommentList(new ArrayList<>());
        user4.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user4.setEmail("jane.doe@example.org");
        user4.setId(1);
        user4.setLikeList(new HashSet<>());
        user4.setName("Name");
        user4.setPassword("iloveyou");
        user4.setPostList(new ArrayList<>());
        user4.setRole("Role");
        user4.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(userService.findUserById(anyInt())).thenReturn(user4);

        User user5 = new User();
        user5.setCommentList(new ArrayList<>());
        user5.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user5.setEmail("jane.doe@example.org");
        user5.setId(1);
        user5.setLikeList(new HashSet<>());
        user5.setName("Name");
        user5.setPassword("iloveyou");
        user5.setPostList(new ArrayList<>());
        user5.setRole("Role");
        user5.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Post post2 = new Post();
        post2.setCommentList(new ArrayList<>());
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setDescription("The characteristics of someone or something");
        post2.setFeaturedImage("Featured Image");
        post2.setId(1);
        post2.setLikeList(new ArrayList<>());
        post2.setSlug("Slug");
        post2.setTitle("Dr");
        post2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setUser(user5);
        when(postService.findPostById(anyInt())).thenReturn(post2);
        assertThrows(PostAlreadyLikedException.class, () -> likeImplementation.like(1, 1, new LikeDto(true)));
        verify(likeRepository).findLikeByUserIdAndPostId(anyInt(), anyInt());
        verify(likeRepository).delete((Like) any());
        verify(userService).findUserById(anyInt());
        verify(postService).findPostById(anyInt());
    }

    @Test
    void testPostAlreadyLike() {
        User user = new User();
        user.setCommentList(new ArrayList<>());
        user.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLikeList(new HashSet<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPostList(new ArrayList<>());
        user.setRole("Role");
        user.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Post post = new Post();
        post.setCommentList(new ArrayList<>());
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDescription("The characteristics of someone or something");
        post.setFeaturedImage("Featured Image");
        post.setId(1);
        post.setLikeList(new ArrayList<>());
        post.setSlug("Slug");
        post.setTitle("Dr");
        post.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setUser(user);

        User user1 = new User();
        user1.setCommentList(new ArrayList<>());
        user1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setLikeList(new HashSet<>());
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPostList(new ArrayList<>());
        user1.setRole("Role");
        user1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Like like = new Like();
        like.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like.setId(1);
        like.setLiked(true);
        like.setPost(post);
        like.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like.setUser(user1);

        User user2 = new User();
        user2.setCommentList(new ArrayList<>());
        user2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setLikeList(new HashSet<>());
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPostList(new ArrayList<>());
        user2.setRole("Role");
        user2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Post post1 = new Post();
        post1.setCommentList(new ArrayList<>());
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setDescription("The characteristics of someone or something");
        post1.setFeaturedImage("Featured Image");
        post1.setId(1);
        post1.setLikeList(new ArrayList<>());
        post1.setSlug("Slug");
        post1.setTitle("Dr");
        post1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setUser(user2);

        User user3 = new User();
        user3.setCommentList(new ArrayList<>());
        user3.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setLikeList(new HashSet<>());
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPostList(new ArrayList<>());
        user3.setRole("Role");
        user3.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Like like1 = new Like();
        like1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like1.setId(1);
        like1.setLiked(true);
        like1.setPost(post1);
        like1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        like1.setUser(user3);
        doNothing().when(likeRepository).delete((Like) any());
        when(likeRepository.findLikeByUserIdAndPostId(anyInt(), anyInt())).thenReturn(like);
        when(likeRepository.save((Like) any())).thenReturn(like1);
        when(likeRepository.likeList(anyInt())).thenReturn(new ArrayList<>());

        User user4 = new User();
        user4.setCommentList(new ArrayList<>());
        user4.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user4.setEmail("jane.doe@example.org");
        user4.setId(1);
        user4.setLikeList(new HashSet<>());
        user4.setName("Name");
        user4.setPassword("iloveyou");
        user4.setPostList(new ArrayList<>());
        user4.setRole("Role");
        user4.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(userService.findUserById(anyInt())).thenReturn(user4);
        when(postService.findPostById(anyInt())).thenThrow(new PostAlreadyLikedException("An error occurred"));
        assertThrows(PostAlreadyLikedException.class, () -> likeImplementation.like(1, 1, new LikeDto(true)));
        verify(userService).findUserById(anyInt());
        verify(postService).findPostById(anyInt());
    }
}

