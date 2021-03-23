package com.epam.atm.preselect.datasource.test.tests.base;

import com.epam.atm.preselect.datasource.datareading.DataReader;
import com.epam.atm.preselect.datasource.datareading.DataWriter;
import com.epam.atm.preselect.datasource.datareading.property.PropertyDataProcessor;
import com.epam.atm.preselect.datasource.test.utils.PropertyUtils;
import java.io.File;

import static com.epam.atm.preselect.datasource.test.constants.GlobalConstants.TEST_FILES_DIRECTORY;

public class BasePropertyTest {

    protected DataReader dataReader;
    protected DataWriter dataWriter;
    protected PropertyUtils propertyUtils;
    protected String fileToReadPath;
    protected String fileToWritePath;

    public BasePropertyTest() {
        fileToReadPath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + ".properties";
        fileToWritePath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + "_user_generated.properties";
        dataReader = new PropertyDataProcessor(new File(fileToReadPath).getAbsolutePath());
        dataWriter = new PropertyDataProcessor(new File(fileToWritePath).getAbsolutePath());
        propertyUtils = new PropertyUtils(fileToReadPath);
    }

}
