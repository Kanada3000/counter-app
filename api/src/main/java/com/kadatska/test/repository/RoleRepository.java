package com.kadatska.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kadatska.test.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
