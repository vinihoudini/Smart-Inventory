package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.dto.OrderDTO;
import com.example.inventory_management_api.model.Order;
import com.example.inventory_management_api.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);

        when(orderService.findById(1L)).thenReturn(Optional.of(order));

        OrderDTO result = orderController.getOrderById(1L);

        assertEquals(1L, result.getId());
        verify(orderService).findById(1L);
    }

    @Test
    void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);

        Order order = new Order();
        order.setId(1L);

        when(orderService.create(any(Order.class))).thenReturn(order);

        OrderDTO result = orderController.createOrder(orderDTO);

        assertEquals(1L, result.getId());
        verify(orderService).create(any(Order.class));
    }

    @Test
    void testUpdateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);

        Order order = new Order();
        order.setId(1L);

        when(orderService.update(eq(1L), any(Order.class))).thenReturn(order);

        OrderDTO result = orderController.updateOrder(1L, orderDTO);

        assertEquals(1L, result.getId());
        verify(orderService).update(eq(1L), any(Order.class));
    }

    @Test
    void testDeleteOrder() {
        orderController.deleteOrder(1L);
        verify(orderService).delete(1L);
    }
}
