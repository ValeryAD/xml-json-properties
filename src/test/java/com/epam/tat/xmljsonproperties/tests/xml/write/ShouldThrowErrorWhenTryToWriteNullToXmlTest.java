package com.epam.tat.xmljsonproperties.tests.xml.write;

import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.tests.base.BaseJsonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShouldThrowErrorWhenTryToWriteNullToXmlTest extends BaseJsonTest {

    @Test
    public void shouldThrowErrorWhenTryToWriteNullToXmlTest() {
        Assertions.assertThrows(UbableToWriteDataToSourceException.class, () ->
                dataWriter.writeDataToSource(null));
    }
}
