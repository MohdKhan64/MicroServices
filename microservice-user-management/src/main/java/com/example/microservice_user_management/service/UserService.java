package com.example.microservice_user_management.service;

import com.example.microservice_user_management.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findByIdList(List<Long> idList);
}
