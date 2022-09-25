package com.graceman.fashionblogrestapi.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Utility {
    public String generateUuid() {
        return java.util.UUID.randomUUID().toString();
    }

}
