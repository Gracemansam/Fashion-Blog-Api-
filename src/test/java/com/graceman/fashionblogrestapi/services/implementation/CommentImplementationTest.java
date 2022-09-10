package com.graceman.fashionblogrestapi.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.CommentRepository;
import com.graceman.fashionblogrestapi.repository.PostRepository;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.CommentResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;
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

@ContextConfiguration(classes = {CommentImplementation.class})
@ExtendWith(SpringExtension.class)
class CommentImplementationTest {
    @Autowired
    private CommentImplementation commentImplementation;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private PostService postService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @Test
    void testComment() {
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

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        comment.setId(1);
        comment.setPost(post);
        comment.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        comment.setUser(user1);
        when(commentRepository.save((Comment) any())).thenReturn(comment);

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
        post1.setUser(user3);
        when(postService.findPostById(anyInt())).thenReturn(post1);

        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        CommentResponse actualCommentResult = commentImplementation.comment(1, 1, commentDto);
        assertEquals("success", actualCommentResult.getMessage());
        Post post2 = actualCommentResult.getPost();
        assertSame(post1, post2);
        Comment comment1 = actualCommentResult.getComment();
        assertSame(post2, comment1.getPost());
        assertEquals("Comment", comment1.getComment());
        assertSame(user2, comment1.getUser());
        verify(commentRepository).save((Comment) any());
        verify(userService).findUserById(anyInt());
        verify(postService).findPostById(anyInt());
    }


    @Test
    void testSearchComment() {
        when(commentRepository.findByCommentContaining((String) any())).thenReturn(new ArrayList<>());
        SearchCommentResponse actualSearchCommentResult = commentImplementation.searchComment("Keyword");
        assertTrue(actualSearchCommentResult.getComments().isEmpty());
        assertEquals("success", actualSearchCommentResult.getMessage());
        verify(commentRepository).findByCommentContaining((String) any());
    }
}

