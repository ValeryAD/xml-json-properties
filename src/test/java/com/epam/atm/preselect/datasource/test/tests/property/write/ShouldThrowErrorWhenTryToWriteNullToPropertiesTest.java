package com.epam.atm.preselect.datasource.test.tests.property.write;

import com.epam.atm.preselect.datasource.exceptions.UbableToWriteDataToSourceException;
import com.epam.atm.preselect.datasource.test.tests.base.BasePropertyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShouldThrowErrorWhenTryToWriteNullToPropertiesTest extends BasePropertyTest {

    @Test
    public void shouldThrowErrorWhenTryToWriteNullToPropertiesTest() {
        Assertions.assertThrows(UbableToWriteDataToSourceException.class, () ->
                dataWriter.writeDataToSource(null));
    }
}
