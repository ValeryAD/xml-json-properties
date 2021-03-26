package com.epam.tat.xmljsonproperties.datareading.property;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.model.AirCompany;

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
