package com.example.inventory_management_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shoppinglist_items",
            joinColumns = @JoinColumn(name = "shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itemsToBuy;


}
