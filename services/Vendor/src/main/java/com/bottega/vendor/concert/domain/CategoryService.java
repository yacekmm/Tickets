package com.bottega.vendor.concert.domain;

import java.util.*;

import com.bottega.sharedlib.ddd.DomainService;
import org.apache.commons.lang3.StringUtils;

@DomainService
public class CategoryService {

    private static final Map<String, String[]> defaultCategories = new HashMap<>();

    static {
        defaultCategories.put("rock", new String[]{"Rock", "Scorpions"});
        defaultCategories.put("superstar", new String[]{"Rihanna"});
    }

    Category categorize(Concert concert) {
        return defaultCategories.entrySet().stream()
                .filter(entry -> StringUtils.containsAnyIgnoreCase(concert.getTitle().getValue(), entry.getValue()))
                .findFirst()
                .map(entry -> Category.from(entry.getKey()))
                .orElse(Category.from("other"));
    }
}
