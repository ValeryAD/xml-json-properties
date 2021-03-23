package com.epam.atm.preselect.datasource.test.tests.json.read;

import com.epam.atm.preselect.datasource.model.AirCompany;
import com.epam.atm.preselect.datasource.test.tests.base.BaseJsonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.epam.atm.preselect.datasource.test.constants.ErrorMessages.WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE;

public class ShouldReadEmptyJsonArrayTest extends BaseJsonTest {

    @Test
    public void shouldReadEmptyJsonArrayTest() {
        jsonUtils.generateEmptyJsonArray();
        AirCompany airCompany = dataReader.readDataFromSource();
        Assertions.assertEquals(0, airCompany.getTotalNumberOfPlanes(), WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE);
    }
}
