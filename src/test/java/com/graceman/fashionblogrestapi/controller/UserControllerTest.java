package com.graceman.fashionblogrestapi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.services.UserService;

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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    @Test
    void testLogin() throws Exception {
        LocalDateTime timeStamp = LocalDateTime.of(1, 1, 1, 1, 1);
        when(userService.login((UserLoginDto) any()))
                .thenReturn(new LoginResponse("Not all who wander are lost", timeStamp, new User()));

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("jane.doe@example.org");
        userLoginDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userLoginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Not all who wander are lost\",\"timeStamp\":[1,1,1,1,1],\"user\":{\"id\":0,\"name\":null,\"email\""
                                        + ":null,\"role\":null,\"password\":null,\"createdAt\":null,\"updatedAt\":null,\"postList\":[],\"commentList\":[],"
                                        + "\"likeList\":[]}}"));
    }


    @Test
    void testRegister() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/register");
        postResult.accept("https://example.org/example");
        MockHttpServletRequestBuilder contentTypeResult = postResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new UserRegisterDto("Name", "jane.doe@example.org", "Role", "iloveyou")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

