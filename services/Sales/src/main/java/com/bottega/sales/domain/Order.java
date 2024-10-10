package com.bottega.sales.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Address shippingAddress;

    @Embedded
    private OrderPriority priority;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Money total;

    public Order(Client client) {
        this.client = client;
        this.status = OrderStatus.CREATED;
        this.total = new Money(0, Currency.PLN);
    }


    public void addProduct(Product product) {
        products.add(product);
        total = total.add(product.getPrice());
    }

    public void submit() {
        this.status = OrderStatus.SUBMITTED;
        if(priority.getType().equals(PriorityType.VIP) && products.size() >= 2) {
            total = total.discount(10);
        }
    }
}