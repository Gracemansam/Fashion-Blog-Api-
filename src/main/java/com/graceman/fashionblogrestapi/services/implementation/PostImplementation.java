package com.graceman.fashionblogrestapi.services.implementation;

import com.graceman.fashionblogrestapi.dto.PostDto;
import com.graceman.fashionblogrestapi.exception.PostNotFoundException;
import com.graceman.fashionblogrestapi.exception.UserNotFoundException;
import com.graceman.fashionblogrestapi.model.Post;
import com.graceman.fashionblogrestapi.model.User;
import com.graceman.fashionblogrestapi.repository.PostRepository;
import com.graceman.fashionblogrestapi.repository.UserRepository;
import com.graceman.fashionblogrestapi.response.CreatePostResponse;
import com.graceman.fashionblogrestapi.response.SearchPostResponse;
import com.graceman.fashionblogrestapi.services.PostService;
import com.graceman.fashionblogrestapi.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class PostImplementation implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private  final UserRepository userRepository;

    private UserService userService;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        Post post = new Post();
        User user = userService.findUserById(postDto.getUser_id());
        post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setSlug(makeSlug(postDto.getTitle()));
        postRepository.save(post);
        return new CreatePostResponse("success" , LocalDateTime.now() , post);
    }
   @Override
    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> postList = postRepository.findByTitleContaining(keyword);
        return new SearchPostResponse("success" , LocalDateTime.now() , postList);
    }
    @Override
    public Post findPostById(int id){
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post With ID: " + id + " Not Found "));
    }





}

