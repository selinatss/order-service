package com.microservices.order_service.controller;

import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    class OrderControllerIT {

        @Mock
        private OrderService orderService;

        @InjectMocks
        private OrderController orderController;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        }

        @Test
        void testPlaceOrder() throws Exception {
            // Arrange
            OrderRequest orderRequest = new OrderRequest(
                    "1",
                    "Iphone 15",
                     new BigDecimal(200),
                     2
            );
            doNothing().when(orderService).placeOrder(orderRequest);

            // Act & Assert
            mockMvc.perform(post("/api/order")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")) // Replace with a valid JSON representation of OrderRequest
                    .andExpect(status().isOk())
                    .andExpect(content().string("Order placed successfully"));
        }
}
