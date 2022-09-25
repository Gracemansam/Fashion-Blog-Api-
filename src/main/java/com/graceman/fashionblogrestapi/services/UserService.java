package com.graceman.fashionblogrestapi.services;

import com.graceman.fashionblogrestapi.dto.AuthRequest;
import com.graceman.fashionblogrestapi.dto.GoogleOauth2;
import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    //   @Autowired
    //    public UserImplementation(UserRepository userRepository, ModelMapper modelmapper) {
    //        this.userRepository = userRepository;
    //        this.modelmapper = modelmapper;
    //    }
    ResponseEntity<ApiResponse> register(UserRegisterDto userDto);

    ResponseEntity<ApiResponse> authenticate(AuthRequest request);

    ResponseEntity<ApiResponse> authenticateOauth2(GoogleOauth2 authPrincipal);

//    ResponseEntity<ApiResponse> login(UserLoginDto loginDto);

    User findUserByEmail(String email);

    User findUserById(int id);
}
