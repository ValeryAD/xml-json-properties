package com.epam.atm.preselect.datasource.test.tests.base;

import com.epam.atm.preselect.datasource.datareading.DataReader;
import com.epam.atm.preselect.datasource.datareading.DataWriter;
import com.epam.atm.preselect.datasource.datareading.json.JsonDataProcessor;
import com.epam.atm.preselect.datasource.test.utils.JsonUtils;

import java.io.File;

import static com.epam.atm.preselect.datasource.test.constants.GlobalConstants.TEST_FILES_DIRECTORY;

public class BaseJsonTest {

    protected DataReader dataReader;
    protected DataWriter dataWriter;
    protected JsonUtils jsonUtils;
    protected String fileToReadPath;
    protected String fileToWritePath;

    public BaseJsonTest() {
        fileToReadPath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + ".json";
        fileToWritePath = TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + "_user_generated.json";
        dataReader = new JsonDataProcessor(new File(fileToReadPath).getAbsolutePath());
        dataWriter = new JsonDataProcessor(new File(fileToWritePath).getAbsolutePath());
        jsonUtils = new JsonUtils(fileToReadPath);
    }

}
