package com.gumtree.domain;

import java.util.Arrays;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public static Gender getByDisplayName(String displayName) {

        return Arrays.stream(values())
                .filter(gender -> gender.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String getDisplayName() {
        return displayName;
    }
}
