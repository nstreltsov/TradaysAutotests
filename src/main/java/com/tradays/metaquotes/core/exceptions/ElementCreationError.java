package com.tradays.metaquotes.core.exceptions;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class ElementCreationError extends RuntimeException {
    public ElementCreationError(String msg) {
        super(msg);
    }
    public ElementCreationError(Throwable e) {
        super(e);
    }
    public ElementCreationError(String msg, Throwable e) {
        super(msg, e);
    }
}

