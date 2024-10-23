package com.example.inventory_management_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<InventoryLog> inventoryLogs;

    @OneToMany(mappedBy = "user")
    private List<ShoppingList> shoppingLists;


    @OneToMany(mappedBy = "user")
    private List<Alert> alerts;

}
