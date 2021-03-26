package com.epam.tat.xmljsonproperties.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static com.epam.tat.xmljsonproperties.constants.GlobalConstants.TEST_FILES_DIRECTORY;

public class PropertyUtils {

    private String testFilePath;

    public PropertyUtils(String testFilePath) {
        this.testFilePath = testFilePath;
    }

    public void generateEmptyPropertyFile() {
        Properties prop = new Properties();
        writePropertiesToFile(prop);
    }

    private void writePropertiesToFile(Properties prop) {
        OutputStream output = null;
        try {
            new File(TEST_FILES_DIRECTORY).mkdirs();
            output = new FileOutputStream(new File(testFilePath).getAbsolutePath());
            prop.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write properties into a file");
        }
    }

}
