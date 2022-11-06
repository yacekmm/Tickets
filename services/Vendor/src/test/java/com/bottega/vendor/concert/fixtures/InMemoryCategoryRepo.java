package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.CategoryRepo;

public class InMemoryCategoryRepo
        extends InMemoryRepo<Category, CategoryId>
        implements CategoryRepo {
}
