package com.example.bank.util;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
public class AppUtil {

    public static <T> String requestResponseToString(ObjectMapper mapper, T request) {
        String body;
        try {
            body = mapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body;
    }
}
