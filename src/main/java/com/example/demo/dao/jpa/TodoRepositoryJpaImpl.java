package com.example.demo.dao.jpa;

import com.example.demo.dao.repository.TodoRepository;
import com.example.demo.model.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoRepositoryJpaImpl implements TodoRepository {

    @PersistenceContext // runtime de yönetilen transaction'u enjekte eder.
    private EntityManager entityManager;

    @Override
    public List<Todo> findAll() {
        return entityManager.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    @Override
    public Todo findById(Long id) {
        return entityManager.find(Todo.class, id);
    }

    @Override
    public List<Todo> findTodoByName(String name) {
        return entityManager.createQuery("select t from Todo t where t.name = :name", Todo.class).setParameter("name", name).getResultList();
    }

    @Override
    public List<Todo> findTodoByUserId(Long userId) {
        return entityManager.createQuery("select t from Todo t where t.user_id = :userId", Todo.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public void create(Todo todo) {
        entityManager.persist(todo);
    }

    @Override
    public Todo update(Todo todo) {
        return entityManager.merge(todo);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Todo.class, id));
    }
}
