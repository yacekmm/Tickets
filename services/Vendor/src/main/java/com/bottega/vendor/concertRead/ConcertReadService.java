package com.bottega.vendor.concertRead;

import com.bottega.vendor.concert.domain.Concert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConcertReadService {

    private final ConcertFinderRepo concertFinderRepo;

    List<Concert> findConcertsForVendor(String vendorId) {
        return concertFinderRepo.findByVendorIdOrderByDateAsc(vendorId);
    }
}
