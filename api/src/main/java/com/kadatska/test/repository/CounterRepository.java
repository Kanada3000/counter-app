package com.kadatska.test.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kadatska.test.model.Counter;
import com.kadatska.test.model.User;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Long> {

    Counter findByUser(User user);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Counter c set c.number = c.number + :val where c.user = :user")
    void increaseByUid(@Param(value = "user") User user, @Param(value = "val") int value);
}
