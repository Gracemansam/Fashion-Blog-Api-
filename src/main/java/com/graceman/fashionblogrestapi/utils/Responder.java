package com.graceman.fashionblogrestapi.utils;

import com.graceman.fashionblogrestapi.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Responder<Object> {
    public ResponseEntity<ApiResponse> success(String message, Object payload) {
        return new ResponseEntity<>(new ApiResponse(message, true, LocalDateTime.now(), payload), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> notFound() {
        return new ResponseEntity<>(new ApiResponse("Request Not Found", true,LocalDateTime.now(), null), HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<ApiResponse> alreadyExisted(String message) {
        return new ResponseEntity<>(new ApiResponse(message, true,LocalDateTime.now(), null), HttpStatus.CONFLICT);
    }
}
