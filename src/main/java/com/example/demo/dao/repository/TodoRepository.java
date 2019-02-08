package com.example.demo.dao.repository;

import com.example.demo.model.Todo;

import java.util.List;

public interface TodoRepository {

    List<Todo> findAll();

    Todo findById(Long id);

    List<Todo> findTodoByName(String name);

    List<Todo> findTodoByUserId(Long userId);

    void create(Todo todo);

    Todo update(Todo todo);

    void delete(Long id);

}
