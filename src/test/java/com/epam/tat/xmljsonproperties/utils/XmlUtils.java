package com.epam.tat.xmljsonproperties.utils;

public class XmlUtils {

    public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private FileUtils fileUtils;

    public XmlUtils(String testFilePath) {
        fileUtils = new FileUtils(testFilePath);
    }

    public void generateEmptyXml() {
        fileUtils.writeToFile(XML_HEAD + "<planes></planes>");
    }


}
