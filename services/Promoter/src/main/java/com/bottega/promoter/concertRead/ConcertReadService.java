package com.bottega.promoter.concertRead;

import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.vo.Money;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConcertReadService {

    private final ConcertFinderRepo concertFinderRepo;
    private final ConcertPriceRepo concertPriceRepo;

    List<Concert> findConcertsForPromoter(String promoterId) {
        return concertFinderRepo.findByPromoterIdOrderByDateAsc(promoterId);
    }

    public void updatePrice(ConcertId concertId, Money price) {
        concertPriceRepo.save(new ConcertPrice(concertId, price.toInt()));
        log.info("Updating price for concert with id: {}, price: {}", concertId, price);
    }
}
