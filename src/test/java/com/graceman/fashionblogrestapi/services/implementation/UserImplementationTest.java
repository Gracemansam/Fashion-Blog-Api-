package com.graceman.fashionblogrestapi.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.exception.UserNotFoundException;
import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.Like;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.InheritingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserImplementation.class})
@ExtendWith(SpringExtension.class)
class UserImplementationTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserImplementation userImplementation;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegister() {
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
        when(userRepository.save((User) any())).thenReturn(user);

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
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user1);
        when(modelMapper.getConfiguration()).thenReturn(new InheritingConfiguration());
        RegisterResponse actualRegisterResult = userImplementation
                .register(new UserRegisterDto("Name", "jane.doe@example.org", "Role", "iloveyou"));
        assertEquals("success", actualRegisterResult.getMessage());
        assertSame(user1, actualRegisterResult.getUser());
        verify(userRepository).save((User) any());
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(modelMapper).getConfiguration();
    }


    @Test
    void testRegister2() {
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(modelMapper.map((Object) any(), (Class<User>) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        when(modelMapper.getConfiguration()).thenReturn(new InheritingConfiguration());
        assertThrows(UserNotFoundException.class,
                () -> userImplementation.register(new UserRegisterDto("Name", "jane.doe@example.org", "Role", "iloveyou")));
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(modelMapper).getConfiguration();
    }


    @Test
    void testValidLogin() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findUserByEmail((String) any())).thenReturn(ofResult);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("jane.doe@example.org");
        userLoginDto.setPassword("iloveyou");
        LoginResponse actualLoginResult = userImplementation.login(userLoginDto);
        assertEquals("success", actualLoginResult.getMessage());
        assertSame(user, actualLoginResult.getUser());
        verify(userRepository).findUserByEmail((String) any());
    }

    @Test
    void testNullLogin2() {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("foo");
        doNothing().when(user).setCommentList((List<Comment>) any());
        doNothing().when(user).setCreatedAt((LocalDateTime) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setLikeList((Set<Like>) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPostList((List<Post>) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUpdatedAt((LocalDateTime) any());
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findUserByEmail((String) any())).thenReturn(ofResult);

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("jane.doe@example.org");
        userLoginDto.setPassword("iloveyou");
        assertNull(userImplementation.login(userLoginDto));
        verify(userRepository).findUserByEmail((String) any());
        verify(user).getPassword();
        verify(user).setCommentList((List<Comment>) any());
        verify(user).setCreatedAt((LocalDateTime) any());
        verify(user).setEmail((String) any());
        verify(user).setId(anyInt());
        verify(user).setLikeList((Set<Like>) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPostList((List<Post>) any());
        verify(user).setRole((String) any());
        verify(user).setUpdatedAt((LocalDateTime) any());
    }

    /**
     * Method under test: {@link UserImplementation#login(UserLoginDto)}
     */
    @Test
    void testNotFoundLogin3() {
        when(userRepository.findUserByEmail((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setCommentList((List<Comment>) any());
        doNothing().when(user).setCreatedAt((LocalDateTime) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setLikeList((Set<Like>) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPostList((List<Post>) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUpdatedAt((LocalDateTime) any());
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

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("jane.doe@example.org");
        userLoginDto.setPassword("iloveyou");
        assertThrows(UserNotFoundException.class, () -> userImplementation.login(userLoginDto));
        verify(userRepository).findUserByEmail((String) any());
        verify(user).setCommentList((List<Comment>) any());
        verify(user).setCreatedAt((LocalDateTime) any());
        verify(user).setEmail((String) any());
        verify(user).setId(anyInt());
        verify(user).setLikeList((Set<Like>) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPostList((List<Post>) any());
        verify(user).setRole((String) any());
        verify(user).setUpdatedAt((LocalDateTime) any());
    }
}