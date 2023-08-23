package com.kadatska.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kadatska.test.model.Counter;
import com.kadatska.test.model.User;
import com.kadatska.test.service.CounterService;

@RestController
public class CounterController {
    
    @Autowired
    private CounterService counterService;

    @GetMapping("/counter")
    @ResponseBody
    public Counter getCounter(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        System.out.println(user.toString());
        Counter counter = counterService.getCounter(user);
        return counter;
    }

    @PostMapping("/counter")
    @ResponseBody
    public void increaseCounter(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        counterService.increaseCounter(user);
    }
}
