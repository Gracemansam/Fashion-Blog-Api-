package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.AuthRequest;
import com.graceman.fashionblogrestapi.dto.GoogleOauth2;
import com.graceman.fashionblogrestapi.dto.UserLoginDto;
import com.graceman.fashionblogrestapi.dto.UserRegisterDto;
import com.graceman.fashionblogrestapi.enums.Provider;
import com.graceman.fashionblogrestapi.exception.UserNotFoundException;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.ApiResponse;
import com.graceman.fashionblogrestapi.security.JwtService;
import com.graceman.fashionblogrestapi.services.UserService;
import com.graceman.fashionblogrestapi.utils.Responder;
import com.graceman.fashionblogrestapi.utils.Utility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor

public class UserImplementation implements UserService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private  final UserRepository userRepository;
    private final ModelMapper modelmapper;
    private final Responder responder;
    private final Utility util;
//   @Autowired
//    public UserImplementation(UserRepository userRepository, ModelMapper modelmapper, Responder responder) {
//        this.userRepository = userRepository;
//        this.modelmapper = modelmapper;
//        this.responder = responder;
//    }

   @Override
    public ResponseEntity<ApiResponse> register(UserRegisterDto userDto) {
       User alreadyExistUser1 = userRepository.findUserByEmail(userDto.getEmail()).orElse(null);
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        if(alreadyExistUser1 == null){
           User user =  new User();
            user = modelmapper.map(userDto, User.class);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setProvider(Provider.LOCAL);
            User result = userRepository.save(user);
            return responder.success("User registered successfully", result);
        }else{
            return responder.alreadyExisted("User already exist");
        }

    }
    @Override
    public ResponseEntity<ApiResponse> authenticate(AuthRequest request){
        System.out.println("I am here !!!!!!!!!!!!!!!!!!");
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(auth.isAuthenticated()){
            String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(request.getEmail(),request.getPassword(),new ArrayList<>()));
            return  responder.success("success",token);
        }else{
            return  responder.notFound("Authentication Failed");
        }
    }
    @Override
    public ResponseEntity<ApiResponse> authenticateOauth2(GoogleOauth2 authPrincipal) {

        User existingUser = userRepository.findByUsername(authPrincipal.getEmail()).orElse(null);
        if(existingUser == null) {
            User user = new User();
            user.setUsername(authPrincipal.getEmail());
            user.setEmail(authPrincipal.getEmail());
            user.setFirstName(authPrincipal.getFirstName());
            user.setLastName(authPrincipal.getLastName());
            user.setProvider(Provider.GOOGLE);
            user.setDisplayPicture(authPrincipal.getDisplayPicture());
            User result = userRepository.save(user);
            String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(result.getEmail(), result.getPassword(), new ArrayList<>()));
            return responder.success("success", token);
        }else{
            String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>()));
            return responder.success("success", token);
        }
    }
    public ResponseEntity<ApiResponse> authenticateOauth2Facebook(FacebookAuthRequest authPrincipal) {
       User existingUser = userRepository.findByUsername(authPrincipal.getEmail()).orElse(null);
            if(existingUser == null) {
                User user = new User();
                user.setUsername(authPrincipal.getEmail());
                user.setEmail(authPrincipal.getEmail());
                user.setFirstName(authPrincipal.getFirstName());
                user.setLastName(authPrincipal.getLastName());
                user.setProvider(Provider.FACEBOOK);
                user.setDisplayPicture(authPrincipal.getDisplayPicture());
                User result = userRepository.save(user);
                String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(result.getEmail(), result.getPassword(), new ArrayList<>()));
                return responder.success("success", token);
            }else{
                String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>()));
                return responder.success("success", token);
            }

    }


//   @Override
//    public ResponseEntity<ApiResponse> login(UserLoginDto loginDto) {
//        User guest = findUserByEmail(loginDto.getEmail());
//        ResponseEntity<ApiResponse> loginResponse = null;
//        if (guest != null){
//            if (guest.getPassword().equals(loginDto.getPassword())){
//                loginResponse = responder.success("Login successful", guest);
//            }
//        }else {
//            loginResponse = responder.notFound("email is incorrect");
//        }
//        return loginResponse;
//    }
    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User With email: " + email + " Not Found "));
    }
   @Override
    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User With ID: " + id + " Not Found "));
    }



}

