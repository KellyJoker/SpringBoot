package com.dxd.demo01.dao;

import com.dxd.demo01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer>, CrudRepository<User,Integer> {
    User findByUsername(String username);
}
