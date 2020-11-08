package com.tradays.metaquotes.cucumber;

import com.tradays.metaquotes.variable.TestVariableHolder;
import cucumber.api.Transformer;

/**
 * @author Nikolay Streltsov on 08.11.2020
 */
public class VariableConverter extends Transformer<Object> {

    @Override
    public Object transform(String s) {
        return TestVariableHolder.evalVariable(s);
    }
}

