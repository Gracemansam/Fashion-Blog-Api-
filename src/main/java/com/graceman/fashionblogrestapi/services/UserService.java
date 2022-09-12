package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.response.LoginResponse;
import com.graceman.fashionblogrestapi.response.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    //   @Autowired
    //    public UserImplementation(UserRepository userRepository, ModelMapper modelmapper) {
    //        this.userRepository = userRepository;
    //        this.modelmapper = modelmapper;
    //    }
    ResponseEntity<ApiResponse> register(UserRegisterDto userDto);

    LoginResponse login(UserLoginDto loginDto);

    User findUserByEmail(String email);

    User findUserById(int id);
}
