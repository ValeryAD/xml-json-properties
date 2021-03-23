package com.epam.atm.preselect.datasource.test.tests.base;

import com.epam.atm.preselect.datasource.datareading.DataReader;
import com.epam.atm.preselect.datasource.datareading.DataWriter;
import com.epam.atm.preselect.datasource.datareading.xml.XmlDataProcessor;
import com.epam.atm.preselect.datasource.test.utils.XmlUtils;

import java.io.File;

import static com.epam.atm.preselect.datasource.test.constants.GlobalConstants.TEST_FILES_DIRECTORY;

public class BaseXmlTest {

    protected DataReader dataReader;
    protected DataWriter dataWriter;
    protected XmlUtils xmlUtils;
    protected String fileToReadPath;
    protected String fileToWritePath;

    public BaseXmlTest() {
        fileToReadPath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + ".xml";
        fileToWritePath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + "_user_generated.xml";
        dataReader = new XmlDataProcessor(new File(fileToReadPath).getAbsolutePath());
        dataWriter = new XmlDataProcessor(new File(fileToWritePath).getAbsolutePath());
        xmlUtils = new XmlUtils(fileToReadPath);
    }

}
