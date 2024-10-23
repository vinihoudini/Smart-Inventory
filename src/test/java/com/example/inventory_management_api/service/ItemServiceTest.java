package com.example.inventory_management_api.service;
import com.example.inventory_management_api.model.Item;
import com.example.inventory_management_api.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void testCreateItem() {
        Item item = new Item();
        item.setName("Tablet");
        item.setQuantity(3);

        Mockito.when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.create(item);
        assertEquals(item.getName(), savedItem.getName());
    }

    @Test
    public void testFindItemById() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Smartphone");

        Mockito.when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));

        Item foundItem = itemService.findById(1L);
        assertNotNull(foundItem);
        assertEquals("Smartphone", foundItem.getName());
    }
}
