package com.gumtree.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.gumtree.error.AddressBookErrors.RESOURCE_DOES_NOT_EXIST;
import static java.util.stream.Collectors.toList;

public class FileHandler {

    public static List<String> readLinesFromFile(String filePath) throws IOException, URISyntaxException {

        URL url = ClassLoader.getSystemResource(filePath);
        if (url == null) {
            throw new FileNotFoundException(RESOURCE_DOES_NOT_EXIST);
        }

        return Files.lines(Paths.get(url.toURI())).collect(toList());
    }
}
