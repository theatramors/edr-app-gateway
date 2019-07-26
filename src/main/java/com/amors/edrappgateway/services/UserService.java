package com.amors.edrappgateway.services;

import com.amors.edrappgateway.dto.UserDto;
import com.amors.edrappgateway.http.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserService {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<UserDto> getUsers() {
        try {
            return objectMapper.readValue(new HttpClient("http://localhost:8101/users").get(), new TypeReference<List<UserDto>>() {});
        } catch (IOException e) {
            return emptyList();
        }
    }

    public UserDto getUser(Long id) {
        return UserDto.builder().username("John").email("john23@mail.com").age(23).build();
    }
}
