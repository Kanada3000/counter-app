package com.kadatska.test;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService{

    private final CounterRepository counterRepository;

    @Override
    public Counter getCounter() {
        return counterRepository.getCounter();
    }

    @Override
    public void increaseCounter() {
        counterRepository.increaseCounter(1);
    }
    
}
