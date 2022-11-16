package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.InMemoryItemPriceRepo;
import org.junit.jupiter.api.*;

public class ItemPriceRepo_DepTest extends FrameworkTestBase {

    private InMemoryItemPriceRepo fakeRepo;
    private ItemPriceRepo realRepo;

    @BeforeEach
    void setUp() {
        fakeRepo = new InMemoryItemPriceRepo();
        realRepo = priceFixtures.itemPriceRepo;
    }

    @Test
    public void inMemoryPriceRepo_isValid() {
        //given
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, "item-id").build();
        ItemPrice otherPrice = builders.aPrice().priceForItem(100_00, "otherId").build();

        //when

        //then

    }

}
