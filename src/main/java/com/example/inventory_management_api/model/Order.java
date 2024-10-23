package com.example.inventory_management_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    // Método para calcular o total baseado nos itens
    public BigDecimal calculateTotal() {
        return items.stream()
                .filter(item -> item.getPrice() != null) // Filtra itens com preço nulo
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void addItem(Item item) {
        this.items.add(item);
        recalculateTotal();
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.total = calculateTotal();
    }
}

