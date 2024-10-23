package com.example.inventory_management_api.controller;


import com.example.inventory_management_api.dto.ItemDTO;
import com.example.inventory_management_api.model.Item;
import com.example.inventory_management_api.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        Item newItem = itemService.createItem(item);
        URI location = URI.create("/api/items/" + newItem.getId());
        return ResponseEntity.created(location).body(convertToDto(newItem));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Item updatedItem = itemService.updateItem(id, convertToEntity(itemDTO));
        return ResponseEntity.ok(convertToDto(updatedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private ItemDTO convertToDto(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private Item convertToEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
}
