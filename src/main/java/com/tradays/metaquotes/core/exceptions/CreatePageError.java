package com.tradays.metaquotes.core.exceptions;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class CreatePageError extends RuntimeException {
    public CreatePageError(String msg) {
        super(msg);
    }
    public CreatePageError(Throwable e) {
        super(e);
    }
    public CreatePageError(String msg, Throwable e) {
        super(msg, e);
    }
}

