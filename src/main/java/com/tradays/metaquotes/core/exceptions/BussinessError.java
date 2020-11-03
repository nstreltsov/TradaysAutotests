package com.tradays.metaquotes.core.exceptions;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class BussinessError extends RuntimeException {
    public BussinessError(String msg) {
        super(msg);
    }
    public BussinessError(Throwable e) {
        super(e);
    }
    public BussinessError(String msg, Throwable e) {
        super(msg, e);
    }
}

