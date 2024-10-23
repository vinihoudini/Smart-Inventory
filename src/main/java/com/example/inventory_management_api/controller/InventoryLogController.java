package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.dto.InventoryLogDTO;
import com.example.inventory_management_api.model.InventoryLog;
import com.example.inventory_management_api.service.InventoryLogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inventory_management_api.service.ItemService;  // Importe o serviço de Item
import com.example.inventory_management_api.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory-logs")
public class InventoryLogController {

    @Autowired
    private InventoryLogService inventoryLogService;



    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<InventoryLogDTO> getAllInventoryLogs() {
        return inventoryLogService.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryLogDTO> getInventoryLog(@PathVariable Long id) {
        InventoryLog log = inventoryLogService.findById(id);
        return ResponseEntity.ok(mapToDTO(log));
    }

    @PostMapping
    public ResponseEntity<InventoryLog> createInventoryLog(@RequestBody InventoryLog inventoryLog) {
        Long userId = inventoryLog.getUser().getId();
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        inventoryLog.setUser(userService.findById(userId));
        inventoryLog.setItem(itemService.findById(inventoryLog.getItem().getId()));

        InventoryLog newLog = inventoryLogService.create(inventoryLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryLogDTO> updateInventoryLog(@PathVariable Long id, @RequestBody InventoryLogDTO logDTO) {
        InventoryLog log = mapToEntity(logDTO);
        InventoryLog updatedLog = inventoryLogService.update(id, log);
        return ResponseEntity.ok(mapToDTO(updatedLog));
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryLog(@PathVariable Long id) {
        inventoryLogService.delete(id);
    }

    private InventoryLogDTO mapToDTO(InventoryLog log) {
        return modelMapper.map(log, InventoryLogDTO.class);
    }

    private InventoryLog mapToEntity(InventoryLogDTO logDTO) {
        return modelMapper.map(logDTO, InventoryLog.class);
    }
}
