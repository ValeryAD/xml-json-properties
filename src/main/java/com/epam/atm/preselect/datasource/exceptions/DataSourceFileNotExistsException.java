package com.epam.atm.preselect.datasource.exceptions;

public class DataSourceFileNotExistsException extends RuntimeException {

    public DataSourceFileNotExistsException(String message) {
        super(message);
    }

    public DataSourceFileNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
