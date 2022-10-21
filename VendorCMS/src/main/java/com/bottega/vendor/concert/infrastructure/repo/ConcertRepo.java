package com.bottega.vendor.concert.infrastructure.repo;

import com.bottega.vendor.concert.domain.Concert;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ConcertRepo extends CrudRepository<Concert, UUID> {

}
