package com.example.inventory_management_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class InventoryLogDTO {
    private Long id;
    private String action;
    private Integer quantityChanged;
    private LocalDate date;
    private Long userId;
    private Long itemId;

}

