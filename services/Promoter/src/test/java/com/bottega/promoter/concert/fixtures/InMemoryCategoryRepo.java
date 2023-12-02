package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.CategoryRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

public class InMemoryCategoryRepo
        extends InMemoryRepo<Category, CategoryId>
        implements CategoryRepo {
}
