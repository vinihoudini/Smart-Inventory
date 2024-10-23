package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.StorageLocation;
import com.example.inventory_management_api.repository.StorageLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageLocationService {

    @Autowired
    private StorageLocationRepository storageLocationRepository;

    public List<StorageLocation> findAll() {
        return storageLocationRepository.findAll();
    }

    public StorageLocation findById(Long id) {
        Optional<StorageLocation> optionalLocation = storageLocationRepository.findById(id);
        return optionalLocation.orElse(null);
    }

    public StorageLocation create(StorageLocation storageLocation) {
        return storageLocationRepository.save(storageLocation);
    }

    public StorageLocation update(StorageLocation storageLocation) {
        return storageLocationRepository.save(storageLocation);
    }

    public void delete(Long id) {
        storageLocationRepository.deleteById(id);
    }
}
