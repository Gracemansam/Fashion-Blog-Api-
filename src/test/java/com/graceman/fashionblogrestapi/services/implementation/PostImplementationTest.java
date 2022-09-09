package com.graceman.fashionblogrestapi.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.exception.PostNotFoundException;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.PostRepository;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.CreatePostResponse;
import com.graceman.fashionblogrestapi.response.SearchPostResponse;
import com.graceman.fashionblogrestapi.services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.InheritingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostImplementation.class})
@ExtendWith(SpringExtension.class)
class PostImplementationTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private PostImplementation postImplementation;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @Test
    void testValidCreatePost() {
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
        when(postRepository.save((Post) any())).thenReturn(post);

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
        post1.setUser(user1);
        when(modelMapper.map((Object) any(), (Class<Post>) any())).thenReturn(post1);
        when(modelMapper.getConfiguration()).thenReturn(new InheritingConfiguration());

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
        when(userService.findUserById(anyInt())).thenReturn(user2);

        PostDto postDto = new PostDto();
        postDto.setDescription("The characteristics of someone or something");
        postDto.setFeaturedImage("Featured Image");
        postDto.setTitle("Dr");
        postDto.setUser_id(1);
        CreatePostResponse actualCreatePostResult = postImplementation.createPost(postDto);
        assertEquals("success", actualCreatePostResult.getMessage());
        Post post2 = actualCreatePostResult.getPost();
        assertSame(post1, post2);
        assertEquals("dr", post2.getSlug());
        assertSame(user2, post2.getUser());
        verify(postRepository).save((Post) any());
        verify(modelMapper).map((Object) any(), (Class<Post>) any());
        verify(modelMapper).getConfiguration();
        verify(userService).findUserById(anyInt());
    }


    @Test
    void testMakeSlug() {
        assertEquals("input", postImplementation.makeSlug("Input"));
    }


    @Test
    void testValidSearchPost() {
        when(postRepository.findByTitleContaining((String) any())).thenReturn(new ArrayList<>());
        SearchPostResponse actualSearchPostResult = postImplementation.searchPost("Keyword");
        assertEquals("success", actualSearchPostResult.getMessage());
        assertTrue(actualSearchPostResult.getPosts().isEmpty());
        verify(postRepository).findByTitleContaining((String) any());
    }


    @Test
    void testInValidSearchPost2() {
        when(postRepository.findByTitleContaining((String) any()))
                .thenThrow(new PostNotFoundException("An error occurred"));
        assertThrows(PostNotFoundException.class, () -> postImplementation.searchPost("Keyword"));
        verify(postRepository).findByTitleContaining((String) any());
    }


    @Test
    void testValidFindPostById() {
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
        Optional<Post> ofResult = Optional.of(post);
        when(postRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(post, postImplementation.findPostById(1));
        verify(postRepository).findById((Integer) any());
    }


    @Test
    void testInValidFindPostById2() {
        when(postRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postImplementation.findPostById(1));
        verify(postRepository).findById((Integer) any());
    }

    @Test
    void testFindPostById3() {
        when(postRepository.findById((Integer) any())).thenThrow(new PostNotFoundException("An error occurred"));
        assertThrows(PostNotFoundException.class, () -> postImplementation.findPostById(1));
        verify(postRepository).findById((Integer) any());
    }
}

