package com.gumtree.processor;

import com.gumtree.domain.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.gumtree.error.AddressBookErrors.EMPTY_STREAM;
import static com.gumtree.error.AddressBookErrors.EMPTY_STREAM_OR_PERSON_DOES_NOT_EXIST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookQueryProcessorTest {

    AddressBookQueryProcessor service = new AddressBookQueryProcessor();

    @Test
    public void countByGenderWhenFileIsEmpty() throws IOException, URISyntaxException {

        long count = service.countByGender(Gender.MALE, "data/address-book/AddressBookTestEmptyFile");
        assertEquals(0, count);
    }

    @Test
    public void countByGenderSuccess() throws IOException, URISyntaxException {

        long count = service.countByGender(Gender.MALE, "data/address-book/AddressBookTest");
        assertEquals(3, count);

        count = service.countByGender(Gender.FEMALE, "data/address-book/AddressBookTest");
        assertEquals(2, count);
    }

    @Test
    public void oldestPersonWhenFileIsEmpty() throws IOException, URISyntaxException {

        try {
            service.oldestPerson("data/address-book/AddressBookTestEmptyFile");
        } catch (IllegalArgumentException e) {

            // We expect this exception to be thrown, assert the correct message and finish the test case
            assertEquals(EMPTY_STREAM, e.getMessage());
            return;
        }

        // We should not reach here. If so, fail the test case
        fail();
    }

    @Test
    public void oldestPersonSuccess() throws IOException, URISyntaxException {

        String oldestPerson = service.oldestPerson("data/address-book/AddressBookTest");

        String expectedOldestPerson = "Wes Jackson";
        assertEquals(expectedOldestPerson, oldestPerson);
    }

    @Test
    public void daysDifferenceWhenFileIsEmpty() throws IOException, URISyntaxException {

        String person1 = "Bill McKnight";
        String person2 = "Paul Robinson";
        try {
            service.daysDifference("data/address-book/AddressBookTestEmptyFile", person1, person2);
        } catch (IllegalArgumentException e) {

            // We expect this exception to be thrown, assert the correct message and finish the test case
            assertEquals(EMPTY_STREAM_OR_PERSON_DOES_NOT_EXIST, e.getMessage());
            return;
        }

        // We should not reach here. If so, fail the test case
        fail();
    }

    @Test
    public void daysDifferenceWhenSuppliedPersonDoesNotExist() throws IOException, URISyntaxException {

        String person1 = "Tom Harvey"; // does not exist
        String person2 = "Paul Robinson";
        try {
            service.daysDifference("data/address-book/AddressBookTest", person1, person2);
        } catch (IllegalArgumentException e) {

            // We expect this exception to be thrown, assert the correct message and finish the test case
            assertEquals(EMPTY_STREAM_OR_PERSON_DOES_NOT_EXIST, e.getMessage());
            return;
        }

        // We should not reach here. If so, fail the test case
        fail();
    }

    @Test
    public void daysDifferenceSuccess() throws IOException, URISyntaxException {

        String person1 = "Bill McKnight";
        String person2 = "Paul Robinson";

        long daysDifference = service.daysDifference("data/address-book/AddressBookTest", person1, person2);

        long expectedDaysDifference = 2862L; // Calculated from timeanddate.com
        assertEquals(expectedDaysDifference, daysDifference);
    }

    @Test
    public void daysDifferenceSuccessWhenPerson1IsYoungerThanPerson2() throws IOException, URISyntaxException {

        String person1 = "Paul Robinson";
        String person2 = "Bill McKnight";

        long daysDifference = service.daysDifference("data/address-book/AddressBookTest", person1, person2);

        long expectedDaysDifference = -2862L;
        assertEquals(expectedDaysDifference, daysDifference);
    }
}
