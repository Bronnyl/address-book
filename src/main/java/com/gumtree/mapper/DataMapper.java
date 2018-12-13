package com.gumtree.mapper;

import com.gumtree.domain.AddressBook;
import com.gumtree.domain.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataMapper {

    public static AddressBook map(String line) {
        String[] splitToken = line.split(", ");
        return new AddressBook(splitToken[0], getGender(splitToken[1]), getDob(splitToken[2]));
    }

    private static Gender getGender(String displayName) {
        return Gender.getByDisplayName(displayName);
    }

    private static LocalDate getDob(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/MM/yy"));
    }
}
