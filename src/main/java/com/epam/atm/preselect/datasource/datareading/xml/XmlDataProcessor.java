package com.epam.atm.preselect.datasource.datareading.xml;

import com.epam.atm.preselect.datasource.datareading.AbstractDataProcessor;
import com.epam.atm.preselect.datasource.model.AirCompany;

public class XmlDataProcessor extends AbstractDataProcessor {

    public XmlDataProcessor(String sourceIdentifier) {
        super(sourceIdentifier);
    }

    @Override
    public AirCompany readDataFromSource() {
        throw new UnsupportedOperationException("You need to implement this method");
    }

    @Override
    public void writeDataToSource(AirCompany aircompany) {
        throw new UnsupportedOperationException("You need to implement this method");
    }
}
