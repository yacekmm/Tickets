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

    public Order(Client client) {
        this.client = client;
        this.status = OrderStatus.CREATED;
    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public void submit() {
        this.status = OrderStatus.SUBMITTED;
    }
}