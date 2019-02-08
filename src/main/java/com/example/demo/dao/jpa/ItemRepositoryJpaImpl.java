package com.example.demo.dao.jpa;

import com.example.demo.dao.repository.ItemRepository;
import com.example.demo.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepositoryJpaImpl implements ItemRepository {

    @PersistenceContext // runtime de yönetilen transaction'u enjekte eder.
    private EntityManager entityManager;

    @Override
    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i", Item.class).getResultList();
    }

    @Override
    public Item findById(Long id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public List<Item> findItemByName(String name) {
        return entityManager.createQuery("select i from Item i where i.name = :name", Item.class).setParameter("name", name).getResultList();
    }

    @Override
    public void create(Item item) {
        entityManager.persist(item);
    }

    @Override
    public Item update(Item item) {
        return entityManager.merge(item);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Item.class, id));
    }

}
