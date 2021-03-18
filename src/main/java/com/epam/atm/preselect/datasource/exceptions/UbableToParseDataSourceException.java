package com.epam.atm.preselect.datasource.exceptions;

public class UbableToParseDataSourceException extends RuntimeException {

    public UbableToParseDataSourceException(String message) {
        super(message);
    }

    public UbableToParseDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
