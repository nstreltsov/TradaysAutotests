package com.tradays.metaquotes.cucumber;

import lombok.Value;

/**
 * Конвертер для Cucumber-методов, конвертирует таблицу вида:
 *        |field    | value       |
 *       | Важность | MEDIUM      |
 *       | Страна   | Switzerland |
 * в массив объектов List<FieldValueTable>
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
@Value
public class FieldValueTable {
    private String field;

    private String value;
}
