package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaOrderLine;

public interface IHamaCustomerOrderService {

    List<HamaCustomerOrder> findAll();

    Optional<HamaCustomerOrder> getOrderById(Long id);

    HamaCustomerOrder getOrCreateOrder(Long customerId);

    HamaCustomerOrder save(HamaCustomerOrder order);

    String addToCart(Long customerId, Long productId, int quantity);

    String removeFromCart(Long customerId, Long productId);

    String checkout(Long customerId);

    List<HamaOrderLine> getOrderLines(Long orderId);
}
