package com.epam.tat.xmljsonproperties.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.epam.tat.xmljsonproperties.constants.GlobalConstants.TEST_FILES_DIRECTORY;

public class FileUtils {

    private String filePath;

    public FileUtils(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String stringToWrite) {
        try {
            new File(TEST_FILES_DIRECTORY).mkdirs();
            Files.write(new File(filePath).toPath(), stringToWrite.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unable to write test JSON into a file");
        }
    }
}
