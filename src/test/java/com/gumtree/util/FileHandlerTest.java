package com.gumtree.util;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.gumtree.error.AddressBookErrors.RESOURCE_DOES_NOT_EXIST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileHandlerTest {

    @Test
    public void readLinesFromFileWhenFileDoesNotExist() throws IOException, URISyntaxException {

        try {
            FileHandler.readLinesFromFile("data/address-book/AddressBookNoSuchFile");
        } catch (FileNotFoundException e) {

            // We expect this exception to be thrown, assert the correct message and finish the test case
            assertEquals(RESOURCE_DOES_NOT_EXIST, e.getMessage());
            return;
        }

        // We should not reach here. If so, fail the test case
        fail();
    }

    @Test
    public void readLinesFromFileWhenFileIsEmpty() throws IOException, URISyntaxException {

        List<String> lines = FileHandler.readLinesFromFile("data/address-book/AddressBookTestEmptyFile");
        assertEquals(0, lines.size());
    }

    @Test
    public void readLinesFromFileSuccess() throws IOException, URISyntaxException {

        List<String> lines = FileHandler.readLinesFromFile("data/address-book/AddressBookTest");

        //Ensure correct number of lines are read and ensure read line is correct by verifying one of them
        assertEquals(5, lines.size());
        String expectedLine1 = "Bill McKnight, Male, 16/03/77";
        assertEquals(expectedLine1, lines.get(0));
    }
}
