package com.bottega.vendor.concert.domain;

import com.bottega.sharedlib.ddd.DomainService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@DomainService
public class TagService {

    private static Map<String, String[]> defaultTags = new HashMap<>();

    static {
        defaultTags.put("rock", new String[]{"Rock", "Scorpions"});
        defaultTags.put("festival", new String[]{"festival"});
        defaultTags.put("pop", new String[]{"Rihanna"});
    }

    public Set<Tag> tag(Title title) {
        Set<Tag> tags = new HashSet<>();
        defaultTags.forEach((tag, requiredSubstrings) -> {
            if (StringUtils.containsAnyIgnoreCase(title.getValue(), requiredSubstrings)) {
                tags.add(Tag.from(tag));
            }
        });
        return tags;
    }

}
