package com.gumtree.domain;

import java.time.LocalDate;

public class AddressBook {

    private String name;

    private Gender gender;

    private LocalDate dob;

    public AddressBook(String name, Gender gender, LocalDate dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }
}


