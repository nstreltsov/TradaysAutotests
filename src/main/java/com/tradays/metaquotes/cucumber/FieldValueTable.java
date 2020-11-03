package com.tradays.metaquotes.cucumber;

import lombok.Value;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Value
public class FieldValueTable {
    private String field;

    private String value;
}
