package com.tradays.metaquotes.cucumber;

import com.tradays.metaquotes.variable.TestVariableHolder;
import cucumber.api.Transformer;

/**
 * Конвертер извлекающий значение переменной, переданной в шаге теста в виде {variable}
 *
 * @author Nikolay Streltsov on 08.11.2020
 */
public class VariableConverter extends Transformer<Object> {

    @Override
    public Object transform(String s) {
        return TestVariableHolder.evalVariable(s);
    }
}

