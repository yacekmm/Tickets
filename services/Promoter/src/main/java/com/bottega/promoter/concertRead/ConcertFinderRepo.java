package com.bottega.promoter.concertRead;

import java.util.List;

import com.bottega.promoter.concert.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertFinderRepo extends CrudRepository<Concert, ConcertId> {

    List<Concert> findByPromoterIdOrderByDateAsc(String promoterId);
}
