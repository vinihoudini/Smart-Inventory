package com.example.inventory_management_api.repository;


import com.example.inventory_management_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByExpirationDateBefore(LocalDate date);
    List<Item> findByCategory(Item.Category category);
}

