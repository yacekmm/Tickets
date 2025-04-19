package com.bottega.payment;

import com.bottega.payment.api.SubmitResponseDto;
import com.bottega.payment.domain.*;
import com.bottega.payment.infra.AddressRepository;
import com.bottega.payment.infra.ClientRepository;
import com.bottega.payment.infra.OrderRepository;
import com.bottega.payment.infra.ProductRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles({"test"})
class OrderTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    public void vipSubmitsOrder() {
        // given
        Address address = new Address("31, Street St.", "City", "Country");
        Client client = new Client("John Doe");
        client.addAddress(address);
        Order order = new Order(client);
        order.setShippingAddress(address);
        order.setPriority(new OrderPriority(PriorityType.VIP, 5));
        Product product = new Product("Gizmo", new Money(2.33, Currency.PLN));
        order.addProduct(product);
        transactionTemplate.executeWithoutResult(status ->
        {
            addressRepository.save(address);
            clientRepository.save(client);
            productRepository.save(product);
            orderRepository.save(order);
        });

        // when
        RestAssured.reset();
        RestAssured.port = port;
        RestAssured.basePath = "/v1";
        SubmitResponseDto response = RestAssured.given()
                .log().all()
                .post("/orders/" + order.getId() + "/submit")
                .then()
                .log().all()
                .extract()
                .as(SubmitResponseDto.class);

        // then
        assertEquals(response.orderStatus(), OrderStatus.SUBMITTED.name());

        Order orderFromDb = orderRepository.findById(order.getId()).get();
        assertEquals(OrderStatus.SUBMITTED, orderFromDb.getStatus());

        List<Product> products = orderFromDb.getProducts();
        assertEquals(1, products.size());

        Product firstProduct = products.get(0);
        assertEquals("Gizmo", firstProduct.getName());
        assertEquals(new Money(2.33, Currency.PLN), firstProduct.getPrice());

        assertEquals(new Money(2.33, Currency.PLN), orderFromDb.getTotal());
    }

    @Test
    public void vipSubmitsOrder2() {
        // given
        Address address = new Address("31, Street St.", "City", "Country");
        Client client = new Client("John Doe");
        client.addAddress(address);
        Order order = new Order(client);
        order.setShippingAddress(address);
        order.setPriority(new OrderPriority(PriorityType.VIP, 5));
        Product product = new Product("Gizmo", new Money(2.33, Currency.PLN));
        Product product2 = new Product("Magic", new Money(5.20, Currency.PLN));
        order.addProduct(product);
        order.addProduct(product2);
        transactionTemplate.executeWithoutResult(status ->
        {
            addressRepository.save(address);
            clientRepository.save(client);
            productRepository.save(product);
            productRepository.save(product2);
            orderRepository.save(order);
        });

        // when
        RestAssured.reset();
        RestAssured.port = port;
        RestAssured.basePath = "/v1";
        SubmitResponseDto response = RestAssured.given()
                .log().all()
                .post("/orders/" + order.getId() + "/submit")
                .then()
                .log().all()
                .extract()
                .as(SubmitResponseDto.class);

        // then
        assertEquals(response.orderStatus(), OrderStatus.SUBMITTED.name());

        Order orderFromDb = orderRepository.findById(order.getId()).get();
        assertEquals(OrderStatus.SUBMITTED, orderFromDb.getStatus());

        List<Product> products = orderFromDb.getProducts();
        assertEquals(2, products.size());

        Product firstProduct = products.get(0);
        assertEquals("Gizmo", firstProduct.getName());
        assertEquals(new Money(2.33, Currency.PLN), firstProduct.getPrice());
        Product secondProduct = products.get(1);
        assertEquals("Magic", secondProduct.getName());
        assertEquals(new Money(5.20, Currency.PLN), secondProduct.getPrice());

        assertEquals(new Money(6.78, Currency.PLN), orderFromDb.getTotal());
    }
}