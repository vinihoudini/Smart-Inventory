package com.example.inventory_management_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter

public class ShoppingListDTO {
    private Long id;
    private Long userId;
    private List<ItemDTO> items;
    private LocalDate creationDate;

}

