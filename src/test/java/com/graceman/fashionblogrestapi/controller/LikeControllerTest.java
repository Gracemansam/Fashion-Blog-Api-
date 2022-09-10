package com.graceman.fashionblogrestapi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graceman.fashionblogrestapi.dto.LikeDto;
import com.graceman.fashionblogrestapi.model.Like;
import com.graceman.fashionblogrestapi.response.LikeResponse;
import com.graceman.fashionblogrestapi.services.LikeService;

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

@ContextConfiguration(classes = {LikeController.class})
@ExtendWith(SpringExtension.class)
class LikeControllerTest {
    @Autowired
    private LikeController likeController;

    @MockBean
    private LikeService likeService;


    @Test
    void testLike() throws Exception {
        LocalDateTime timeStamp = LocalDateTime.of(1, 1, 1, 1, 1);
        when(likeService.like(anyInt(), anyInt(), (LikeDto) any()))
                .thenReturn(new LikeResponse("Not all who wander are lost", timeStamp, new Like(), 1));

        LikeDto likeDto = new LikeDto();
        likeDto.setLiked(true);
        String content = (new ObjectMapper()).writeValueAsString(likeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/like/{user_id}/{post_id}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(likeController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Not all who wander are lost\",\"timeStamp\":[1,1,1,1,1],\"like\":{\"id\":0,\"createdAt\":null,"
                                        + "\"updatedAt\":null,\"liked\":false},\"totalLikes\":1}"));
    }
}

