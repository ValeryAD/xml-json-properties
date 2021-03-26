package com.epam.tat.xmljsonproperties.utils;

public class JsonUtils {

    private FileUtils fileUtils;

    public JsonUtils(String testFilePath) {
        fileUtils = new FileUtils(testFilePath);
    }

    public void generateEmptyJsonArray() {
        fileUtils.writeToFile("[]");
    }

}
