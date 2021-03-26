package com.epam.tat.xmljsonproperties.exceptions;

public class DataSourceFileNotExistsException extends RuntimeException {

    public DataSourceFileNotExistsException(String message) {
        super(message);
    }

    public DataSourceFileNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
