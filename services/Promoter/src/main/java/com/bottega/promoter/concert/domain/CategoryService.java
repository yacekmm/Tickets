package com.bottega.promoter.concert.domain;

import java.util.*;

import com.bottega.sharedlib.ddd.DomainService;
import static org.apache.commons.lang3.StringUtils.containsAnyIgnoreCase;

@DomainService
public class CategoryService {

    private static final Map<String, String[]> defaultCategories = new HashMap<>();

    static {
        defaultCategories.put("rock", new String[]{"Rock", "Scorpions"});
        defaultCategories.put("superstar", new String[]{"Rihanna"});
    }

    public Category categorize(Title title) {
        return defaultCategories.entrySet().stream()
                .filter(entry -> containsAnyIgnoreCase(title.getValue(), entry.getValue()))
                .findFirst()
                .map(entry -> Category.from(entry.getKey()))
                .orElse(Category.from("other"));
    }
}
