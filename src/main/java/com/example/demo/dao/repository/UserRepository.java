package com.example.demo.dao.repository;

import com.example.demo.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    User findUserByUserName(String username);

    void create(User user);

    User update(User user);

    void delete(Long id);

}
