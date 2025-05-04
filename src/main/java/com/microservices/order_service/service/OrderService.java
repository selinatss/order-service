package com.microservices.order_service.service;

import com.microservices.order_service.clients.InventoryClient;
import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.modal.Order;
import com.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
       var isProductIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
       if(isProductIsInStock){
           Order order = new Order();
           order.setOrderNumber(UUID.randomUUID().toString());
           order.setSkuCode(orderRequest.skuCode());
           order.setPrice(orderRequest.price());
           order.setQuantity(orderRequest.quantity());
           orderRepository.save(order);
       }else {
           throw new RuntimeException("Product with sku code " + orderRequest.skuCode() + " is not in stock");
       }
    }
}
