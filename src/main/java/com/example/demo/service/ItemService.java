package com.example.demo.service;

import com.example.demo.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findItems();

    List<Item> findItemByName(String name);

    Item findItemById(Long id);

    void createItem(Item item);

    Item update(Item item);

    void deleteItem(Long id);

}
