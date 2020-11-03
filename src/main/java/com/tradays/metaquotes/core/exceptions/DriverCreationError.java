package com.tradays.metaquotes.core.exceptions;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class DriverCreationError extends RuntimeException {
    public DriverCreationError(String msg) {
        super(msg);
    }
    public DriverCreationError(Throwable e) {
        super(e);
    }
    public DriverCreationError(String msg, Throwable e) {
        super(msg, e);
    }
}
