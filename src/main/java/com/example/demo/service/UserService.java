package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();

    User findUserByUserName(String username);

    User findUserById(Long id);

    void createUser(User user);

    User update(User user);

    void deleteUser(Long id);

}
