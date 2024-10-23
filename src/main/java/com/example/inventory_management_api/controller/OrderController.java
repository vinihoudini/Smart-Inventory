package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.dto.ItemDTO;
import com.example.inventory_management_api.dto.OrderDTO;
import com.example.inventory_management_api.model.Item;
import com.example.inventory_management_api.model.Order;
import com.example.inventory_management_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderService.findAll();
        // Converta para DTO se necessário
        return orders.stream().map(this::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return convertToDTO(order);
    }


    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order createdOrder = orderService.create(order);
        return convertToDTO(createdOrder);
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        // Busque o pedido existente
        Order existingOrder = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualize o pedido existente com os novos dados
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.getItems().clear(); // Limpa os itens existentes
        for (ItemDTO itemDTO : orderDTO.getItemsOrdered()) {
            Item item = new Item();
            item.setId(itemDTO.getItemId());
            item.setQuantity(itemDTO.getQuantity());
            existingOrder.addItem(item);
        }

        // Recalcule o total após atualizar os itens
        existingOrder.setTotal(existingOrder.calculateTotal());

        // Salve e retorne o pedido atualizado
        Order updatedOrder = orderService.update(id, existingOrder);
        return convertToDTO(updatedOrder);
    }



    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }


    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());
        return dto;
    }

    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());


        if (dto.getItemsOrdered() != null) {
            for (ItemDTO itemDTO : dto.getItemsOrdered()) {
                Item item = new Item();
                item.setId(itemDTO.getItemId());
                item.setQuantity(itemDTO.getQuantity());
                order.addItem(item);
            }
        }

        return order;
    }

}
