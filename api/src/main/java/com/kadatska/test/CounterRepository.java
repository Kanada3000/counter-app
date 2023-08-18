package com.kadatska.test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CounterRepository {

    private final JdbcTemplate jdbcTemplate;

    public Counter getCounter(){
        return jdbcTemplate.query("SELECT * FROM counter_table LIMIT 1", BeanPropertyRowMapper.newInstance(Counter.class)).get(0);
    }

    public void increaseCounter(int value){
        jdbcTemplate.update("UPDATE counter_table SET number = number + ?", value);
    }
}
