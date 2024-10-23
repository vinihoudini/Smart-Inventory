package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.Alert;
import com.example.inventory_management_api.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    public Alert findById(Long id) {
        return alertRepository.findById(id).orElseThrow(() -> new RuntimeException("Alert não encontrado"));
    }

    public Alert create(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert update(Long id, Alert updatedAlert) {
        Alert existingAlert = findById(id);

        existingAlert.setMessage(updatedAlert.getMessage());
        existingAlert.setAlertType(updatedAlert.getAlertType());
        existingAlert.setItem(updatedAlert.getItem());

        return alertRepository.save(existingAlert);
    }

    public void delete(Long id) {
        alertRepository.deleteById(id);
    }
}
