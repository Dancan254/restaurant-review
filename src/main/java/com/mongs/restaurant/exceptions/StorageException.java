package com.mongs.restaurant.exceptions;

public class StorageException extends BaseException{

    public StorageException() {
        super();
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }

    public StorageException(String message, String fileName) {
        super(message + " " + fileName);
    }
}
