package com.example.inventory_management_api.functional;

import com.example.inventory_management_api.dto.OrderDTO;
import com.example.inventory_management_api.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderFunctionalTest extends AbstractFunctionalTest {

    @Test
    void testCreateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDate.now());
        orderDTO.setStatus(OrderStatus.PENDENTE);
        orderDTO.setUserId(1L);
        // Adicione outros campos necessários

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated());
    }

    // Adicione mais testes para outros endpoints como getAllOrders e deleteOrder
}
