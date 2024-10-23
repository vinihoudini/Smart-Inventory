package com.example.inventory_management_api.service;


import com.example.inventory_management_api.model.Item;
import com.example.inventory_management_api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {

    @Autowired

    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getItemsNearExpiration(LocalDate date) {
        return itemRepository.findByExpirationDateBefore(date);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        existingItem.setName(item.getName());
        existingItem.setCategory(item.getCategory());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setExpirationDate(item.getExpirationDate());
        return itemRepository.save(existingItem);
    }
}

