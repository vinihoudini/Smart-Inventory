package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.model.StorageLocation;
import com.example.inventory_management_api.service.StorageLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/storage-locations")
public class StorageLocationController {
    @Autowired
    private StorageLocationService storageLocationService;

    @GetMapping
    public List<StorageLocation> getAllStorageLocations() {
        return storageLocationService.findAll();
    }

    @GetMapping("/{id}")
    public StorageLocation getStorageLocation(@PathVariable Long id) {
        return storageLocationService.findById(id);
    }

    @PostMapping
    public StorageLocation createStorageLocation(@RequestBody StorageLocation storageLocation) {
        return storageLocationService.create(storageLocation);
    }

    @PutMapping("/{id}")
    public StorageLocation updateStorageLocation(@PathVariable Long id, @RequestBody StorageLocation storageLocation) {
        storageLocation.setId(id);
        return storageLocationService.update(storageLocation);
    }

    @DeleteMapping("/{id}")
    public void deleteStorageLocation(@PathVariable Long id) {
        storageLocationService.delete(id);
    }
}

