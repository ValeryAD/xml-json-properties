package com.epam.atm.preselect.datasource.datareading;

public abstract class AbstractDataProcessor implements DataReader, DataWriter {

    private String sourceIdentifier;

    public AbstractDataProcessor(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }
}
