package com.epam.tat.xmljsonproperties.tests.xml.read;

import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.tests.base.BaseXmlTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.epam.tat.xmljsonproperties.constants.ErrorMessages.WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE;

public class ShouldReadEmptyXmlTest extends BaseXmlTest {

    @Test
    public void shouldReadEmptyXmlTest() {
        xmlUtils.generateEmptyXml();
        AirCompany airCompany = dataReader.readDataFromSource();
        Assertions.assertEquals(0, airCompany.getTotalNumberOfPlanes(), WRONG_NUMBER_OF_PLANES_EMPTY_SOURCE);
    }
}
