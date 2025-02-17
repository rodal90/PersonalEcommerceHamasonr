package com.core.Hamasonr.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaCustomer;
import com.core.Hamasonr.data.model.HamaOrderLine;
import com.core.Hamasonr.data.model.HamaProduct;
import com.core.Hamasonr.data.repository.IHamaCustomerOrderRepository;
import com.core.Hamasonr.data.repository.IHamaCustomerRepository;
import com.core.Hamasonr.data.repository.IHamaOrderLineRepository;
import com.core.Hamasonr.data.repository.IHamaProductRepository;
import com.core.Hamasonr.service.IHamaCustomerOrderService;

@Service
public class HamaCustomerOrderServiceImpl implements IHamaCustomerOrderService {

    @Autowired
    private IHamaCustomerOrderRepository orderRepository;

    @Autowired
    private IHamaOrderLineRepository orderLineRepository;

    @Autowired
    private IHamaProductRepository productRepository;

    @Autowired
    private IHamaCustomerRepository customerRepository;

    @Override
    public List<HamaCustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<HamaCustomerOrder> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public HamaCustomerOrder getOrCreateOrder(Long customerId) {
        Optional<HamaCustomerOrder> existingOrder = orderRepository
                .findByHamaCustomerIdAndOrderStatus(customerId, "Progress");

        return existingOrder.orElseGet(() -> {
            HamaCustomer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            HamaCustomerOrder newOrder = new HamaCustomerOrder();
            newOrder.setCreationDate(LocalDateTime.now());
            newOrder.setOrderStatus("Progress");
            newOrder.setHamaCustomer(customer);
            return orderRepository.save(newOrder);
        });
    }

    @Override
    public HamaCustomerOrder save(HamaCustomerOrder order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public String addToCart(Long customerId, Long productId, int quantity) {
        HamaCustomerOrder order = getOrCreateOrder(customerId);
        HamaProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<HamaOrderLine> existingOrderLine = order.getOrderLines().stream()
                .filter(line -> line.getProduct().getId().equals(productId))
                .findFirst();

        if (existingOrderLine.isPresent()) {
            existingOrderLine.get().setQuantity(existingOrderLine.get().getQuantity() + quantity);
        } else {
            HamaOrderLine newOrderLine = new HamaOrderLine();
            newOrderLine.setHamaCustomerOrder(order);
            newOrderLine.setProduct(product);
            newOrderLine.setQuantity(quantity);
            newOrderLine.setPricePerUnit(product.getPrice());
            newOrderLine.setDiscountApplied(BigDecimal.ZERO);
            order.getOrderLines().add(newOrderLine);
        }

        orderRepository.save(order);
        return "Producto agregado al carrito.";
    }

    @Override
    @Transactional
    public String removeFromCart(Long customerId, Long productId) {
        HamaCustomerOrder order = getOrCreateOrder(customerId);
        boolean removed = order.getOrderLines().removeIf(line -> line.getProduct().getId().equals(productId));
        orderRepository.save(order);
        return removed ? "Producto eliminado del carrito." : "Producto no encontrado en el carrito.";
    }

    @Override
    @Transactional
    public String checkout(Long customerId) {
        HamaCustomerOrder order = getOrCreateOrder(customerId);
        order.setOrderStatus("Finished");
        orderRepository.save(order);
        return "Compra finalizada.";
    }

    @Override
    public List<HamaOrderLine> getOrderLines(Long orderId) {
        return orderRepository.findById(orderId)
                .map(HamaCustomerOrder::getOrderLines)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
}
