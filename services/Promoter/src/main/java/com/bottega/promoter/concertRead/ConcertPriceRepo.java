package com.bottega.promoter.concertRead;

import com.bottega.promoter.concert.domain.ConcertId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertPriceRepo extends CrudRepository<ConcertPrice, ConcertId> {

}
