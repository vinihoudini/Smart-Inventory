package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.ShoppingList;
import com.example.inventory_management_api.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingListService {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList findById(Long id) {
        return shoppingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping List não encontrada"));
    }

    public ShoppingList create(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList update(Long id, ShoppingList shoppingListDetails) {
        ShoppingList existingShoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping List não encontrada"));

        existingShoppingList.setName(shoppingListDetails.getName());
        existingShoppingList.setItemsToBuy(shoppingListDetails.getItemsToBuy());

        return shoppingListRepository.save(existingShoppingList);
    }

    public void delete(Long id) {
        shoppingListRepository.deleteById(id);
    }
}


