package com.graceman.fashionblogrestapi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.services.PostService;

import java.time.LocalDateTime;

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

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @Autowired
    private PostController postController;

    @MockBean
    private PostService postService;


    @Test
    void testCreate() throws Exception {
        LocalDateTime timeStamp = LocalDateTime.of(1, 1, 1, 1, 1);
        when(postService.createPost((PostDto) any()))
                .thenReturn(new CreatePostResponse("Not all who wander are lost", timeStamp, new Post()));

        PostDto postDto = new PostDto();
        postDto.setDescription("The characteristics of someone or something");
        postDto.setFeaturedImage("Featured Image");
        postDto.setTitle("Dr");
        postDto.setUser_id(1);
        String content = (new ObjectMapper()).writeValueAsString(postDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/createPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Not all who wander are lost\",\"timeStamp\":[1,1,1,1,1],\"post\":{\"id\":0,\"title\":null,\"description"
                                        + "\":null,\"slug\":null,\"featuredImage\":null,\"createdAt\":null,\"updatedAt\":null,\"commentList\":[],\"likeList"
                                        + "\":[]}}"));
    }
}

