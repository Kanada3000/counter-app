package com.kadatska.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kadatska.test.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUid(String uid);
    User findByPhoneNumber(String phoneNumber);
}
