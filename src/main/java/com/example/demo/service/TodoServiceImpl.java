package com.example.demo.service;

import com.example.demo.dao.repository.TodoRepository;
import com.example.demo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class) // check exceptionlarda tespit edilip rollback yapılır.
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> findTodoByName(String name) {
        return todoRepository.findTodoByName(name);
    }

    @Override
    public Todo findTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void createTodo(Todo todo) {
        todoRepository.create(todo);
    }

    @Override
    public Todo update(Todo todo) {
        return todoRepository.update(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.delete(id);
    }

}
