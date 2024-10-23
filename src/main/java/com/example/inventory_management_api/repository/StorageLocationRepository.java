package com.example.inventory_management_api.repository;

import com.example.inventory_management_api.model.StorageLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageLocationRepository extends JpaRepository<StorageLocation, Long> {
}
