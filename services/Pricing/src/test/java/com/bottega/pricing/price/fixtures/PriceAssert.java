package com.bottega.pricing.price.fixtures;

import com.bottega.pricing.price.domain.ItemPrice;
import com.bottega.pricing.price.domain.PriceFactor;
import com.bottega.pricing.price.domain.PriceId;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.sharedlib.fixtures.RepoEntries;
import com.bottega.sharedlib.vo.Money;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(staticName = "assertThatPrice")
public class PriceAssert {
    
    private final ItemPrice price;

    public PriceAssert isPersistedIn(ItemPriceRepo repo, RepoEntries entries) {
        assertThat(repo.findAll()).hasSize(entries.allEntriesCount());
        assertThat(repo.findById(price.getId())).hasValue(price);
        return this;
    }

    public PriceAssert hasPrice(int expectedPrice) {
        assertThat(price.getPrice()).isEqualTo(new Money(expectedPrice));
        return this;
    }

    public PriceAssert hasId(PriceId expectedId) {
        assertThat(price.getId()).isEqualTo(expectedId);
        return this;
    }

    public PriceAssert hasItemId(String expectedItemId) {
        assertThat(price.getItemId()).isEqualTo(expectedItemId);
        return this;
    }

    public PriceAssert hasFactors(int count, Consumer<List<PriceFactor>> factorsConsumer) {
        assertThat(price.getPriceFactors()).hasSize(count);
        factorsConsumer.accept(price.getPriceFactors());
        return this;
    }

    public PriceAssert hasNoFactors() {
        assertThat(price.getPriceFactors()).isEmpty();
        return this;
    }
}
