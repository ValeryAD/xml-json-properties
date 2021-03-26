package com.epam.tat.xmljsonproperties.tests.base;

import com.epam.tat.xmljsonproperties.constants.GlobalConstants;
import com.epam.tat.xmljsonproperties.datareading.DataReader;
import com.epam.tat.xmljsonproperties.datareading.DataWriter;
import com.epam.tat.xmljsonproperties.datareading.property.PropertyDataProcessor;
import com.epam.tat.xmljsonproperties.utils.PropertyUtils;

import java.io.File;

public class BasePropertyTest {

    protected DataReader dataReader;
    protected DataWriter dataWriter;
    protected PropertyUtils propertyUtils;
    protected String fileToReadPath;
    protected String fileToWritePath;

    public BasePropertyTest() {
        fileToReadPath = GlobalConstants.TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + ".properties";
        fileToWritePath = GlobalConstants.TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + "_user_generated.properties";
        dataReader = new PropertyDataProcessor(new File(fileToReadPath).getAbsolutePath());
        dataWriter = new PropertyDataProcessor(new File(fileToWritePath).getAbsolutePath());
        propertyUtils = new PropertyUtils(fileToReadPath);
    }

}
