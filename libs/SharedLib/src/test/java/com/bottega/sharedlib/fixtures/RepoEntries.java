package com.bottega.sharedlib.fixtures;

public record RepoEntries(
        int allEntriesCount) {


    public static final RepoEntries SINGULAR = new RepoEntries(1);


}
