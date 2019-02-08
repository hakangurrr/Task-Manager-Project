package com.example.demo.service;

import com.example.demo.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findTodos();

    List<Todo> findTodoByName(String name);

    Todo findTodoById(Long id);

    void createTodo(Todo todo);

    Todo update(Todo todo);

    void deleteTodo(Long id);

}
