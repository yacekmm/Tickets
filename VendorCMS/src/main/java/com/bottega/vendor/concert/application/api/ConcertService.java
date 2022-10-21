package com.bottega.vendor.concert.application.api;

import com.bottega.vendor.concert.infrastructure.repo.ConcertRepo;
import com.bottega.vendor.shared.ddd.ApplicationService;
import lombok.AllArgsConstructor;

//TODO: create a library with DDD annotations, reused in all services
@ApplicationService
@AllArgsConstructor
public class ConcertService {

    private final ConcertRepo concertRepo;



}
