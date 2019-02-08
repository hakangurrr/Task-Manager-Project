package com.example.demo.dao.repository;

import com.example.demo.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findAll();

    Item findById(Long id);

    List<Item> findItemByName(String name);

    void create(Item item);

    Item update(Item item);

    void delete(Long id);


}
