package com.kadatska.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadatska.test.model.Counter;
import com.kadatska.test.model.User;
import com.kadatska.test.repository.CounterRepository;

@Service
public class CounterServiceImpl implements CounterService{

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public Counter getCounter(User user) {
        return counterRepository.findByUser(user);
    }

    @Override
    public void increaseCounter(User user) {
        counterRepository.increaseByUid(user, 1);
    }

    public void createCounter(User user){
        Counter counter = new Counter();
        counter.setUser(user);
        counter.setNumber(0);
        counterRepository.save(counter);
    }
    
}
