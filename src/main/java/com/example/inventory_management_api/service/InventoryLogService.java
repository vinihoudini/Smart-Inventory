package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.InventoryLog;
import com.example.inventory_management_api.repository.InventoryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLogService {

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    public List<InventoryLog> findAll() {
        return inventoryLogRepository.findAll();
    }

    public InventoryLog findById(Long id) {
        return inventoryLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory log não encontrado"));
    }

    public InventoryLog create(InventoryLog log) {
        return inventoryLogRepository.save(log);
    }

    public void delete(Long id) {
        inventoryLogRepository.deleteById(id);
    }

    public InventoryLog update(Long id, InventoryLog updatedLog) {
        InventoryLog existingLog = inventoryLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory log não encontrado"));

        existingLog.setItem(updatedLog.getItem());
        existingLog.setQuantity(updatedLog.getQuantity());
        existingLog.setAction(updatedLog.getAction());
        existingLog.setTimestamp(updatedLog.getTimestamp());

        return inventoryLogRepository.save(existingLog);
    }
}
