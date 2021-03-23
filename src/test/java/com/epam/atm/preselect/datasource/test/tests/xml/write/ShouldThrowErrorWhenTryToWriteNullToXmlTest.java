package com.epam.atm.preselect.datasource.test.tests.xml.write;

import com.epam.atm.preselect.datasource.exceptions.UbableToWriteDataToSourceException;
import com.epam.atm.preselect.datasource.test.tests.base.BaseJsonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShouldThrowErrorWhenTryToWriteNullToXmlTest extends BaseJsonTest {

    @Test
    public void shouldThrowErrorWhenTryToWriteNullToXmlTest() {
        Assertions.assertThrows(UbableToWriteDataToSourceException.class, () ->
                dataWriter.writeDataToSource(null));
    }
}
