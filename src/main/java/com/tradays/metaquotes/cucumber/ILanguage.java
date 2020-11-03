package com.tradays.metaquotes.cucumber;

import java.util.Map;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public interface ILanguage {
    String getName();
    Map<String, String> getMapping();
}
