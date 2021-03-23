package com.epam.atm.preselect.datasource.test.tests.property.read;

import com.epam.atm.preselect.datasource.model.AirCompany;
import com.epam.atm.preselect.datasource.test.tests.base.BasePropertyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.epam.atm.preselect.datasource.test.constants.ErrorMessages.WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE;

public class ShouldReadEmptyPropertiesTest extends BasePropertyTest {

    @Test
    public void shouldReadEmptyPropertiesTest() {
        propertyUtils.generateEmptyPropertyFile();
        AirCompany airCompany = dataReader.readDataFromSource();
        Assertions.assertEquals(0, airCompany.getTotalNumberOfPlanes(), WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE);
    }
}
