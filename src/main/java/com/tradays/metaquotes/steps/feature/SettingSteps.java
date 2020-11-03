package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.ILanguage;
import com.tradays.metaquotes.cucumber.LanguageMatchingConverter;
import com.tradays.metaquotes.steps.scenario.SettingScenarioSteps;
import cucumber.api.Transform;
import cucumber.api.java.ru.Дано;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public class SettingSteps {
    private SettingScenarioSteps settingScenarioSteps = new SettingScenarioSteps();

    @Дано("^установлен язык приложения \"([^\"]*)\"$")
    public void checkFieldValue(@Transform(LanguageMatchingConverter.class) ILanguage language){
        settingScenarioSteps.setLanguage(language);
    }
}
