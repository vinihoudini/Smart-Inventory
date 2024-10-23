package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.model.ShoppingList;
import com.example.inventory_management_api.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {
    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.findAll();
    }

    @GetMapping("/{id}")
    public ShoppingList getShoppingList(@PathVariable Long id) {
        return shoppingListService.findById(id);
    }

    @PostMapping
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.create(shoppingList);
    }

    @PutMapping("/{id}")
    public ShoppingList updateShoppingList(@PathVariable Long id, @RequestBody ShoppingList shoppingList) {
        return shoppingListService.update(id, shoppingList);
    }

    @DeleteMapping("/{id}")
    public void deleteShoppingList(@PathVariable Long id) {
        shoppingListService.delete(id);
    }
}
