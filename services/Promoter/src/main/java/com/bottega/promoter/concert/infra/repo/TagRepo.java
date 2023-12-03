package com.bottega.promoter.concert.infra.repo;

import com.bottega.promoter.concert.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends CrudRepository<Tag, TagId> {
}
