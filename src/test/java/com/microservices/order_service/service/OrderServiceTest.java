package com.microservices.order_service.service;
import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.modal.Order;
import com.microservices.order_service.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest(
                "1",
                "Iphone 15",
                new BigDecimal(200),
                2
        );
        // Act
        orderService.placeOrder(orderRequest);

        // Assert
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();

        assertEquals("Iphone 15", savedOrder.getSkuCode());
        assertEquals(new BigDecimal(200), savedOrder.getPrice());
        assertEquals(2, savedOrder.getQuantity());
    }
}
