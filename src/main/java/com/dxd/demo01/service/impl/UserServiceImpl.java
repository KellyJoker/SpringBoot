package com.dxd.demo01.service.impl;

import com.dxd.demo01.dao.UserRepository;
import com.dxd.demo01.domain.User;
import com.dxd.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            User user = optional.get();
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
