package com.bottega.sales.domain;

import com.bottega.sales.infra.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order submitOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();
        order.submit();
        orderRepository.save(order);
        return order;
    }
}
