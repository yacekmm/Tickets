package com.bottega.sharedlib.tests;

public record RepoEntries(
        int allEntriesCount) {


    public static final RepoEntries SINGULAR = new RepoEntries(1);


}
