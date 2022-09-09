package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.exception.UserNotFoundException;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.LoginResponse;
import com.graceman.fashionblogrestapi.response.RegisterResponse;
import com.graceman.fashionblogrestapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class UserImplementation implements UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelmapper;
   @Autowired
    public UserImplementation(UserRepository userRepository, ModelMapper modelmapper) {
        this.userRepository = userRepository;
        this.modelmapper = modelmapper;
    }

   @Override
    public RegisterResponse register(UserRegisterDto userDto) {
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user =  new User();
        user = modelmapper.map(userDto, User.class);
        userRepository.save(user);
        return new RegisterResponse("success" , LocalDateTime.now() , user);
    }


   @Override
    public LoginResponse login(UserLoginDto loginDto) {
        User guest = findUserByEmail(loginDto.getEmail());
        LoginResponse loginResponse = null;
        if (guest != null){
            if (guest.getPassword().equals(loginDto.getPassword())){
                loginResponse = new LoginResponse("success" , LocalDateTime.now(),guest);
            }
        }else {
            loginResponse = new LoginResponse("password MisMatch" , LocalDateTime.now(), null);
        }
        return loginResponse;
    }
    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User With email: " + email + " Not Found "));
    }
   @Override
    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User With ID: " + id + " Not Found "));
    }



}

