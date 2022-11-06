package com.bottega.vendor.concertRead;

import com.bottega.vendor.concert.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertFinder extends CrudRepository<Concert, ConcertId> {

    List<Concert> findByVendorIdOrderByDateAsc(String vendorId);
}
