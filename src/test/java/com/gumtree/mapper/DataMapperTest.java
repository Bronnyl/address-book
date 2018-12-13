package com.gumtree.mapper;

import com.gumtree.domain.AddressBook;
import com.gumtree.domain.Gender;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DataMapperTest {

    @Test
    public void mapSuccess() {

        String inputLine = "Bill McKnight, Male, 16/03/77";
        AddressBook addressBook = DataMapper.map(inputLine);

        assertEquals("Bill McKnight", addressBook.getName());
        assertEquals(Gender.MALE, addressBook.getGender());
        assertEquals(LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("d/MM/yy")), addressBook.getDob());
    }
}
