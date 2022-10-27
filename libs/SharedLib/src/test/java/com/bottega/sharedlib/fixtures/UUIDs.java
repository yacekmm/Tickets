package com.bottega.sharedlib.fixtures;

import org.apache.commons.lang3.StringUtils;

public class UUIDs {

    private static final String PREFIX = "00000000-0000-0000-0000-";

    public static String ending(int sufix) {
        return PREFIX + StringUtils.leftPad(String.valueOf(sufix), 12, "0");
    }

    public static String zeros() {
        return ending(0);
    }
}
