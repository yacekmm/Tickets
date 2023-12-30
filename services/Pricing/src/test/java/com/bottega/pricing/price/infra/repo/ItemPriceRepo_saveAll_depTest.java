package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.fixtures.InMemoryItemPriceRepo;
import org.junit.jupiter.api.*;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPriceRepo_saveAll_depTest extends FrameworkTestBase {

    private InMemoryItemPriceRepo fakeRepo;
    private ItemPriceRepo realRepo;

    @BeforeEach
    void setUp() {
        //TODO initialize both repos
    }

    @Test
    public void saveAll_isValid() {
        //given
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, "item-id").build();
        ItemPrice otherPrice = builders.aPrice().priceForItem(100_00, "otherId").build();

        //when
        //TODO save in both repos

        //then

    }

}
