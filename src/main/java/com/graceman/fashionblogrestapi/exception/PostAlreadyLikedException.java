package com.graceman.fashionblogrestapi.exception;

public class PostAlreadyLikedException extends RuntimeException {
    public PostAlreadyLikedException(String message) {
        super(message);
    }


}
