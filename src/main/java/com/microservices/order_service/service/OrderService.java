package com.microservices.order_service.service;

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

    public void placeOrder(OrderRequest orderRequest) {
       Order order = new Order();
         order.setOrderNumber(UUID.randomUUID().toString());
         order.setSkuCode(orderRequest.skuCode());
         order.setPrice(orderRequest.price());
         order.setQuantity(orderRequest.quantity());

       orderRepository.save(order);
    }

}
