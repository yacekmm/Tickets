package com.bottega.sales.api;

import com.bottega.sales.domain.Order;
import com.bottega.sales.domain.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/v1/orders/{orderId}/submit")
    public SubmitResponseDto submitOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = orderService.submitOrder(orderId);
        return new SubmitResponseDto(order.getStatus().name());
    }

}
