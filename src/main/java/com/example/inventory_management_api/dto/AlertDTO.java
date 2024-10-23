package com.example.inventory_management_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class AlertDTO {
    private Long id;
    private String message;
    private LocalDate dateCreated;
    private String alertType;
    private Long userId;
    private Long itemId;

}

