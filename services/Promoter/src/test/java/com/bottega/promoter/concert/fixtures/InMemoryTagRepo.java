package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.*;
import com.bottega.promoter.concert.infra.repo.TagRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

public class InMemoryTagRepo
        extends InMemoryRepo<Tag, TagId>
        implements TagRepo {
}
