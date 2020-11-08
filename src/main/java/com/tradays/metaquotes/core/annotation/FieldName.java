package com.tradays.metaquotes.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Помечаются элементы PageObject,
 *  по значению аннотации выполняется поиск необходимо поля внутри PageObject
 *
 *  * @author Nikolay Streltsov on 01.11.2020
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface FieldName {
    String value();
}
