package com.gumtree.processor;

import com.gumtree.domain.AddressBook;
import com.gumtree.domain.Gender;
import com.gumtree.mapper.DataMapper;
import com.gumtree.util.FileHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gumtree.error.AddressBookErrors.EMPTY_STREAM;
import static com.gumtree.error.AddressBookErrors.EMPTY_STREAM_OR_PERSON_DOES_NOT_EXIST;

public class AddressBookQueryProcessor {

    /**
     * Returns count based on the gender
     * @param gender - The gender whose count is required
     * @param filePath - The file path from where data needs to be processed
     * @return Returns zero if the file is empty else returns the count by the gender
     * @throws IOException
     * @throws URISyntaxException
     */
    public long countByGender(Gender gender, String filePath) throws IOException, URISyntaxException {

        return getAddressBookData(filePath)
                .filter(addressBook -> addressBook.getGender().equals(gender))
                .count();
    }

    /**
     * Returns the name of oldest person
     * @param filePath - The file path from where data needs to be processed
     * @return Returns the name of the oldest person
     * @throws IllegalArgumentException when the file is empty
     * @throws IOException
     * @throws URISyntaxException
     */
    public String oldestPerson(String filePath) throws IOException, URISyntaxException {

        Comparator<AddressBook> dobComparator = (addressBook1, addressBook2) -> addressBook1.getDob().isBefore(addressBook2.getDob()) ? 1 : -1;

        AddressBook oldestPerson = getAddressBookData(filePath)
                .collect(Collectors.maxBy(dobComparator))
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_STREAM));

        return oldestPerson.getName();
    }

    /**
     * Returns the number of days person1 is older than person2
     * @param filePath - The file path from where data needs to be processed
     * @param person1 - First person
     * @param person2 - Second person
     * @return Returns the number of days person1 is older than person2.
     *         If person1 is younger than person2, then a negative number(which corresponds to the number of days
     *         person1 is younger than person2) is returned
     * @throws IllegalArgumentException when the file is empty or the supplied person(s) do not exist
     * @throws IOException
     * @throws URISyntaxException
     */
    public long daysDifference(String filePath, String person1, String person2) throws IOException, URISyntaxException {

        AddressBook firstPerson = getPerson(filePath, person1);
        AddressBook secondPerson = getPerson(filePath, person2);

        return ChronoUnit.DAYS.between(firstPerson.getDob(), secondPerson.getDob());
    }

    private Stream<AddressBook> getAddressBookData(String filePath) throws IOException, URISyntaxException {
        return FileHandler.readLinesFromFile(filePath)
                .stream()
                .map(DataMapper::map);
    }

    private AddressBook getPerson(String filePath, String personName) throws IOException, URISyntaxException {

        return getAddressBookData(filePath)
                .filter(addressBook -> addressBook.getName().equalsIgnoreCase(personName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_STREAM_OR_PERSON_DOES_NOT_EXIST));
    }
}
