package com.example.inventory_management_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ItemDTO {
    private Long itemId;

    private Long id;
    private String name;
    private String category;
    private Integer quantity;
    private LocalDate expirationDate;
    private LocalDate purchaseDate;
    private StorageLocationDTO storageLocation;

}

