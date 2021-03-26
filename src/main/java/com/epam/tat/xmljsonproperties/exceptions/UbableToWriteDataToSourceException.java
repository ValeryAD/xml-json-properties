package com.epam.tat.xmljsonproperties.exceptions;

public class UbableToWriteDataToSourceException extends RuntimeException {
    public UbableToWriteDataToSourceException(String message) {
        super(message);
    }

    public UbableToWriteDataToSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
