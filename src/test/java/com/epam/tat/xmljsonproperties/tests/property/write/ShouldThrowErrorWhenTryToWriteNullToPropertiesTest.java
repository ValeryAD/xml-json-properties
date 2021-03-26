package com.epam.tat.xmljsonproperties.tests.property.write;

import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.tests.base.BasePropertyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShouldThrowErrorWhenTryToWriteNullToPropertiesTest extends BasePropertyTest {

    @Test
    public void shouldThrowErrorWhenTryToWriteNullToPropertiesTest() {
        Assertions.assertThrows(UbableToWriteDataToSourceException.class, () ->
                dataWriter.writeDataToSource(null));
    }
}
