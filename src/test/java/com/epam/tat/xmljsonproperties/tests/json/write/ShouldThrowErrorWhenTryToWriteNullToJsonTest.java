package com.epam.tat.xmljsonproperties.tests.json.write;

import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.tests.base.BaseJsonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShouldThrowErrorWhenTryToWriteNullToJsonTest extends BaseJsonTest {

    @Test
    public void shouldThrowErrorWhenTryToWriteNullToJsonTest() {
        Assertions.assertThrows(UbableToWriteDataToSourceException.class, () ->
                dataWriter.writeDataToSource(null));
    }
}
