package com.example.demo.service;

import com.example.demo.dao.repository.ItemRepository;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class) // check exceptionlarda tespit edilip rollback yapılır.
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findItemByName(String name) {
        return itemRepository.findItemByName(name);
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public void createItem(Item item) {
        itemRepository.create(item);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.update(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
