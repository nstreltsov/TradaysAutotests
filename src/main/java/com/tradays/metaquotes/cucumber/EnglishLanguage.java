package com.tradays.metaquotes.cucumber;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
@Value
public class EnglishLanguage implements ILanguage{

    private String name = "English";

    Map<String, String> mapping = new HashMap<>();

    public EnglishLanguage() {
        mapping.put("Русский", "English");
        mapping.put("Русский", "English");
        mapping.put("Deutsch", "Englisch");
        mapping.put("português", "Inglês");
    }
}
