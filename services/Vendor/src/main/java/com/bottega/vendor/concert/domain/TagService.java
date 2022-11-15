package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainService;

import java.util.*;

@DomainService
public class TagService {

    private static final Map<String, String[]> defaultTags = new HashMap<>();

    static {
        defaultTags.put("rock", new String[]{"Rock", "Scorpions"});
        defaultTags.put("festival", new String[]{"festival"});
        defaultTags.put("pop", new String[]{"Rihanna"});
    }

    public Set<Tag> tag(Title title) {
        return null;
    }

}
