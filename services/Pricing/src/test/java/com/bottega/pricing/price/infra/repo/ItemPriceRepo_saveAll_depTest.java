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
        fakeRepo = new InMemoryItemPriceRepo();
        realRepo = priceFixtures.itemPriceRepo;
    }

    @Test
    public void saveAll_isValid() {
        //given
        ItemPrice itemPrice = builders.aPrice().priceForItem(100_00, "item-id").build();
        ItemPrice otherPrice = builders.aPrice().priceForItem(100_00, "otherId").build();

        //when
        fakeRepo.saveAll(of(itemPrice, otherPrice));
        realRepo.saveAll(of(itemPrice, otherPrice));

        //then
        assertThat(realRepo.findByItemId("item-id")).containsExactlyInAnyOrder(itemPrice);
        assertThat(realRepo.findAll()).containsExactlyInAnyOrder(itemPrice, otherPrice);

        assertThat(fakeRepo.findByItemId("item-id")).containsExactlyInAnyOrder(itemPrice);
        assertThat(fakeRepo.findAll()).containsExactlyInAnyOrder(itemPrice, otherPrice);
    }

}
