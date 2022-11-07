package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.InMemoryPriceRepo;
import org.junit.jupiter.api.*;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPriceRepo_DepTest extends FrameworkTestBase {

    private InMemoryPriceRepo inMemoryRepo;

    @BeforeEach
    void setUp() {
        inMemoryRepo = new InMemoryPriceRepo();
    }

    @Test
    public void inMemoryPriceRepo_isValid() {
        //given
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, "item-id").build();
        ItemPrice otherPrice = builders.aPrice().priceForItem(100_00, "otherId").build();

        //when
        inMemoryRepo.saveAll(of(itemPrice, otherPrice));
        priceFixtures.priceRepo.saveAll(of(itemPrice, otherPrice));

        //then
        assertThat(priceFixtures.priceRepo.findByItemId("item-id")).containsExactlyInAnyOrder(itemPrice);
        assertThat(priceFixtures.priceRepo.findAll()).containsExactlyInAnyOrder(itemPrice, otherPrice);

        assertThat(inMemoryRepo.findByItemId("item-id")).containsExactlyInAnyOrder(itemPrice);
        assertThat(inMemoryRepo.findAll()).containsExactlyInAnyOrder(itemPrice, otherPrice);
    }

}
