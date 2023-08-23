package com.kadatska.test.service;

import com.kadatska.test.model.Counter;
import com.kadatska.test.model.User;

public interface CounterService {
    Counter getCounter(User user);
    void increaseCounter(User user);
    void createCounter(User user);
}
