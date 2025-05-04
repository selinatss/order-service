package com.microservices.order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="inventory", url="${inventory.url}")
public interface InventoryClient {
    // The method signature should match the endpoint in the InventoryController
    @GetMapping(value = {"/api/inventory"}, consumes = {"application/json"}, produces = {"application/json"})
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
