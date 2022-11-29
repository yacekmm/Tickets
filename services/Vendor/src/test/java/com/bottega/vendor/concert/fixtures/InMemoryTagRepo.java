package com.bottega.vendor.concert.fixtures;

import com.bottega.sharedlib.infra.repo.InMemoryRepo;
import com.bottega.vendor.concert.domain.*;
import com.bottega.vendor.concert.infra.repo.TagRepo;

public class InMemoryTagRepo
        extends InMemoryRepo<Tag, TagId>
        implements TagRepo {
}
