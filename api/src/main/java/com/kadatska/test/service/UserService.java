package com.kadatska.test.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kadatska.test.model.User;

public interface UserService extends UserDetailsService{
    User getUserById(String uid);
    User save(User user);
}
