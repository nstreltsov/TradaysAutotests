package com.tradays.metaquotes.core.exceptions;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class NoSuchPageError extends RuntimeException {
    public NoSuchPageError(String msg) {
        super(msg);
    }
    public NoSuchPageError(Throwable e) {
        super(e);
    }
    public NoSuchPageError(String msg, Throwable e) {
        super(msg, e);
    }
}

