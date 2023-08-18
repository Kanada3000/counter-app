package com.kadatska.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    
    @Autowired
    private CounterService counterService;

    @GetMapping("/get-counter")
    public Counter getCounter(){
        return counterService.getCounter();
    }

    @PostMapping("/increase-counter")
    public void increaseCounter(){
        counterService.increaseCounter();
    }
}
