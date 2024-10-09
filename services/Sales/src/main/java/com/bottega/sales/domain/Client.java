package com.bottega.sales.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
}
