package com.core.Hamasonr.controllerImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.core.Hamasonr.controller.IHamaCustomerOrderController;
import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaOrderLine;
import com.core.Hamasonr.service.IHamaCustomerOrderService;

@RestController
public class HamaCustomerOrderControllerImpl implements IHamaCustomerOrderController {

    @Autowired
    private IHamaCustomerOrderService orderService;

    @Override
    public List<HamaCustomerOrder> getAllOrders() {
        return orderService.findAll();
    }

    @Override
    public Optional<HamaCustomerOrder> getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @Override
    public String addToCart(Long customerId, Long productId, int quantity) {
        return orderService.addToCart(customerId, productId, quantity);
    }

    @Override
    public String removeFromCart(Long customerId, Long productId) {
        return orderService.removeFromCart(customerId, productId);
    }

    @Override
    public String checkout(Long customerId) {
        return orderService.checkout(customerId);
    }

    @Override
    public List<HamaOrderLine> getOrderLines(Long orderId) {
        return orderService.getOrderLines(orderId);
    }
}