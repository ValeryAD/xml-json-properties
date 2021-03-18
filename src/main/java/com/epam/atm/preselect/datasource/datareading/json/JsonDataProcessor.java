package com.epam.atm.preselect.datasource.datareading.json;

import com.epam.atm.preselect.datasource.datareading.AbstractDataProcessor;
import com.epam.atm.preselect.datasource.model.AirCompany;

public class JsonDataProcessor extends AbstractDataProcessor {

    public JsonDataProcessor(String sourceIdentifier) {
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
