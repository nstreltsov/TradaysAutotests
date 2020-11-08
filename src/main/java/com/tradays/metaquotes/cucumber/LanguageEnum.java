package com.tradays.metaquotes.cucumber;

/**
 * Список реализованых способов переключения на необходимый язык
 * В данный момент реализовано только для English
 *
 * @author Nikolay Streltsov on 03.11.2020
 */
public enum LanguageEnum {
    English(EnglishLanguage.class);

    public final Class<? extends ILanguage> aClass;

    LanguageEnum(Class<? extends ILanguage> aClass) {
        this.aClass = aClass;
    }
}
