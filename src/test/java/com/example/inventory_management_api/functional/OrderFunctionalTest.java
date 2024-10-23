package com.example.inventory_management_api.functional;

import com.example.inventory_management_api.dto.ItemDTO;
import com.example.inventory_management_api.dto.OrderDTO;
import com.example.inventory_management_api.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderFunctionalTest extends AbstractFunctionalTest {

    @Test
    void testCreateOrder() throws Exception {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(1L);
        itemDTO.setQuantity(2);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDate.now());
        orderDTO.setStatus(OrderStatus.PENDENTE);
        orderDTO.setUserId(1L);
        orderDTO.setItemsOrdered(List.of(itemDTO));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated());
    }
}
