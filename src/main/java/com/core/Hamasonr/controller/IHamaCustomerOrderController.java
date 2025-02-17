package com.core.Hamasonr.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaOrderLine;

public interface IHamaCustomerOrderController {

    @GetMapping("/cart/all")
    List<HamaCustomerOrder> getAllOrders();

    @GetMapping("/cart/{orderId}")
    Optional<HamaCustomerOrder> getOrderById(@PathVariable Long orderId);

    @PostMapping("/cart/add")
    String addToCart(@RequestParam Long customerId, 
                     @RequestParam Long productId, 
                     @RequestParam int quantity);
    
    

    @DeleteMapping("/cart/remove")
    String removeFromCart(@RequestParam Long customerId, 
                          @RequestParam Long productId);

    @PostMapping("/cart/checkout")
    String checkout(@RequestParam Long customerId);

    @GetMapping("/cart/order-lines/{orderId}")
    List<HamaOrderLine> getOrderLines(@PathVariable Long orderId);
}