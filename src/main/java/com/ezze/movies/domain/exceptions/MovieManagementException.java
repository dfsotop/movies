package com.ezze.movies.domain.exceptions;

public class MovieManagementException extends Exception {
    public MovieManagementException(Throwable cause) {
        super(cause);
    }

    public MovieManagementException(String message) {
        super(message);
    }
}
