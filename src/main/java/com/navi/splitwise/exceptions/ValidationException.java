package com.navi.splitwise.exceptions;

/**
 * @author Abhinav Tripathi 15/03/21
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
