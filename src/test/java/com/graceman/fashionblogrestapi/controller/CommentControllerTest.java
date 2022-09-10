package com.graceman.fashionblogrestapi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graceman.fashionblogrestapi.dto.CommentDto;
import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.response.CommentResponse;
import com.graceman.fashionblogrestapi.response.SearchCommentResponse;
import com.graceman.fashionblogrestapi.services.CommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentService commentService;

    @Test
    void testComment() throws Exception {
        LocalDateTime timeStamp = LocalDateTime.of(1, 1, 1, 1, 1);
        Comment comment = new Comment();
        when(commentService.comment(anyInt(), anyInt(), (CommentDto) any()))
                .thenReturn(new CommentResponse("Not all who wander are lost", timeStamp, comment, new Post()));

        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        String content = (new ObjectMapper()).writeValueAsString(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/comment/{user_id}/{post_id}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Not all who wander are lost\",\"timeStamp\":[1,1,1,1,1],\"comment\":{\"id\":0,\"comment\":null,"
                                        + "\"createdAt\":null,\"updatedAt\":null},\"post\":{\"id\":0,\"title\":null,\"description\":null,\"slug\":null,"
                                        + "\"featuredImage\":null,\"createdAt\":null,\"updatedAt\":null,\"commentList\":[],\"likeList\":[]}}"));
    }

    @Test
    void testCommentResponse() throws Exception {
        LocalDateTime timeStamp = LocalDateTime.of(1, 1, 1, 1, 1);
        when(commentService.searchComment((String) any()))
                .thenReturn(new SearchCommentResponse("Not all who wander are lost", timeStamp, new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/searchComment/{keyword}",
                "Keyword");
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Not all who wander are lost\",\"timeStamp\":[1,1,1,1,1],\"comments\":[]}"));
    }
}

