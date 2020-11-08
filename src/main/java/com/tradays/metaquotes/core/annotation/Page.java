package com.tradays.metaquotes.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечаются классы,
 * содержащие PageObject элементы, по значению аннотации ищется класс
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface Page {
    String value();
}