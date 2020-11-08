package com.tradays.metaquotes.cucumber;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Содержит описание того как пекрелючиться на English-язык приложения, если выбран другой язык
 *
 * @author Nikolay Streltsov on 03.11.2020
 */
@Value
public class EnglishLanguage implements ILanguage{

    private String name = "English";

    Map<String, String> mapping = new HashMap<>();

    /**
     * инструкция переключения с указанного языка на English
     * ключ - выбранный язык приложения
     * значение - наименование английского языка у языка, который является ключом
     */
    public EnglishLanguage() {
        mapping.put("Русский", "English");
        mapping.put("Deutsch", "Englisch");
        mapping.put("português", "Inglês");
    }
}
