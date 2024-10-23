package com.example.inventory_management_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;



    @Enumerated(EnumType.STRING)
    private Category category;

    private int quantity;

    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "storage_location_id", nullable = false)
    private StorageLocation storageLocation;

    private LocalDate expirationDate;

    public enum Category {
        COMIDA, ROUPA, ELETRONICO, FARMACIA
    }
    public BigDecimal getPrice() {
        return price;
    }



}

