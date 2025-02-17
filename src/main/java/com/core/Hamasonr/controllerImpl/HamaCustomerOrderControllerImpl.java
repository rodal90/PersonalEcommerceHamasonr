package com.core.Hamasonr.controllerImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaOrderLine;
import com.core.Hamasonr.service.IHamaCustomerOrderService;

@RestController
@RequestMapping("/cart")
public class HamaCustomerOrderControllerImpl {

    @Autowired
    private IHamaCustomerOrderService orderService;

    @GetMapping("/all")
    public List<HamaCustomerOrder> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{orderId}")
    public Optional<HamaCustomerOrder> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long customerId, 
                            @RequestParam Long productId, 
                            @RequestParam int quantity) {
        return orderService.addToCart(customerId, productId, quantity);
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Long customerId, 
                                 @RequestParam Long productId) {
        return orderService.removeFromCart(customerId, productId);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Long customerId) {
        return orderService.checkout(customerId);
    }

    @GetMapping("/order-lines/{orderId}")
    public List<HamaOrderLine> getOrderLines(@PathVariable Long orderId) {
        return orderService.getOrderLines(orderId);
    }
}