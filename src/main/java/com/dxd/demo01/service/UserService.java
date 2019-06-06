package com.dxd.demo01.service;

import com.dxd.demo01.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void saveAll(List<User> users);

    void delete(Integer id);

    void update(User user);

    User findOne(Integer id);

    List<User> findAll();
}
