package com.example.inventory_management_api.dto;

import com.example.inventory_management_api.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private OrderStatus status;
    private Long userId;
    private Long supplierId;
    private List<ItemDTO> itemsOrdered;
    private BigDecimal total;
}
