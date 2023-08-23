package com.kadatska.test.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kadatska.test.model.Role;
import com.kadatska.test.model.User;
import com.kadatska.test.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CounterService counterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserById(String uid) {
        return userRepository.findByUid(uid);
    }

    @Override
    public User save(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        counterService.createCounter(user);
        return userRepository.save(user);
    }
    
}
