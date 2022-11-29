package com.bottega.vendor.concert.infra.repo;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.domain.ConcertId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepo extends CrudRepository<Concert, ConcertId> {

}
