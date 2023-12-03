package com.bottega.promoter.concert.infra.repo;

import com.bottega.promoter.concert.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, CategoryId> {
}
