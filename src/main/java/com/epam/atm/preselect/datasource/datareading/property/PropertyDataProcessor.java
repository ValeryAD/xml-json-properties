package com.epam.atm.preselect.datasource.datareading.property;

import com.epam.atm.preselect.datasource.datareading.AbstractDataProcessor;
import com.epam.atm.preselect.datasource.model.AirCompany;

public class PropertyDataProcessor extends AbstractDataProcessor {

    public PropertyDataProcessor(String sourceIdentifier) {
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
