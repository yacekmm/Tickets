package com.bottega.promoter.concert.domain;

import java.util.*;

import com.bottega.sharedlib.ddd.DomainService;
import static org.apache.commons.lang3.StringUtils.containsAnyIgnoreCase;

@DomainService
public class TagService {

    private static final Map<String, String[]> defaultTags = new HashMap<>();

    static {
        defaultTags.put("rock", new String[]{"Rock", "Scorpions"});
        defaultTags.put("festival", new String[]{"festival"});
        defaultTags.put("pop", new String[]{"Rihanna"});
    }

    public Set<Tag> tag(Title title) {
        Set<Tag> tags = new HashSet<>();
        defaultTags.forEach((tag, requiredSubstrings) -> {
            if (containsAnyIgnoreCase(title.getValue(), requiredSubstrings)) {
                tags.add(Tag.from(tag));
            }
        });
        return tags;
    }

}
