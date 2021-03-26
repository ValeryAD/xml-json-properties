package com.epam.tat.xmljsonproperties.tests.base;

import com.epam.tat.xmljsonproperties.constants.GlobalConstants;
import com.epam.tat.xmljsonproperties.datareading.DataReader;
import com.epam.tat.xmljsonproperties.datareading.DataWriter;
import com.epam.tat.xmljsonproperties.datareading.json.JsonDataProcessor;
import com.epam.tat.xmljsonproperties.utils.JsonUtils;

import java.io.File;

public class BaseJsonTest {

    protected DataReader dataReader;
    protected DataWriter dataWriter;
    protected JsonUtils jsonUtils;
    protected String fileToReadPath;
    protected String fileToWritePath;

    public BaseJsonTest() {
        fileToReadPath = GlobalConstants.TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + ".json";
        fileToWritePath = GlobalConstants.TEST_FILES_DIRECTORY + File.separator + getClass().getSimpleName() + "_user_generated.json";
        dataReader = new JsonDataProcessor(new File(fileToReadPath).getAbsolutePath());
        dataWriter = new JsonDataProcessor(new File(fileToWritePath).getAbsolutePath());
        jsonUtils = new JsonUtils(fileToReadPath);
    }

}
