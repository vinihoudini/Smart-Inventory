package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.Item;
import com.example.inventory_management_api.model.ShoppingList;
import com.example.inventory_management_api.repository.ShoppingListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Test
    public void testUpdateShoppingList() {
        ShoppingList existingShoppingList = new ShoppingList();
        existingShoppingList.setId(1L);
        existingShoppingList.setName("Groceries");

        ShoppingList updatedDetails = new ShoppingList();
        updatedDetails.setName("Updated Groceries");

        List<Item> newItems = new ArrayList<>();
        updatedDetails.setItemsToBuy(newItems);

        Mockito.when(shoppingListRepository.findById(1L)).thenReturn(Optional.of(existingShoppingList));
        Mockito.when(shoppingListRepository.save(any(ShoppingList.class))).thenReturn(existingShoppingList);

        ShoppingList updatedShoppingList = shoppingListService.update(1L, updatedDetails);
        assertEquals("Updated Groceries", updatedShoppingList.getName());
        assertEquals(newItems, updatedShoppingList.getItemsToBuy());
    }
}
