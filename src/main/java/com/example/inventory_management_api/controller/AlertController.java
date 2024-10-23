package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.dto.AlertDTO;
import com.example.inventory_management_api.model.Alert;
import com.example.inventory_management_api.service.AlertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AlertDTO> getAllAlerts() {
        return alertService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AlertDTO getAlert(@PathVariable Long id) {
        Alert alert = alertService.findById(id);
        return convertToDTO(alert);
    }

    @PostMapping
    public AlertDTO createAlert(@RequestBody AlertDTO alertDTO) {
        Alert alert = convertToEntity(alertDTO);
        Alert newAlert = alertService.create(alert);
        return convertToDTO(newAlert);
    }

    @PutMapping("/{id}")
    public AlertDTO updateAlert(@PathVariable Long id, @RequestBody AlertDTO alertDTO) {
        Alert alert = convertToEntity(alertDTO);
        Alert updatedAlert = alertService.update(id, alert);
        return convertToDTO(updatedAlert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertService.delete(id);
    }

    private AlertDTO convertToDTO(Alert alert) {
        return modelMapper.map(alert, AlertDTO.class);
    }

    private Alert convertToEntity(AlertDTO alertDTO) {
        return modelMapper.map(alertDTO, Alert.class);
    }
}
