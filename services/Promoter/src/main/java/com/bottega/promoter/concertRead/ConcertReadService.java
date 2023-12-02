package com.bottega.promoter.concertRead;

import java.util.List;

import com.bottega.promoter.concert.domain.Concert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConcertReadService {

    private final ConcertFinderRepo concertFinderRepo;

    List<Concert> findConcertsForPromoter(String promoterId) {
        return concertFinderRepo.findByPromoterIdOrderByDateAsc(promoterId);
    }
}
