package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.exceptions.BussinessError;
import com.tradays.metaquotes.core.field.RadioButton;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.cucumber.ILanguage;
import com.tradays.metaquotes.page.BottomBar;
import com.tradays.metaquotes.page.EventsPage;
import com.tradays.metaquotes.page.SettingsPage;
import io.qameta.allure.Step;

import java.util.List;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public class SettingScenarioSteps {

    private FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();

    @Step("установлен язык приложения \"$language\"")
    public void setLanguage(ILanguage language){
        AbstractPageObject.setCurrentPage(BottomBar.class);
        fieldScenarioSteps.clickField("Настройки");
        AbstractPageObject.setCurrentPage(SettingsPage.class);
        String currentLanguage = fieldScenarioSteps.getFieldValue("Выбранный язык");
        if (!currentLanguage.equals(language.getName())){
            fieldScenarioSteps.clickField("Выбранный язык");
            List<RadioButton> availableLanguages = AbstractPageObject.getCurrentPage().getFields("Доступные языки");
            RadioButton selectLanguage = availableLanguages.stream()
                    .filter(availableLanguage -> availableLanguage.getText().equals(language.getMapping().get(currentLanguage)))
                    .findAny()
                    .orElseThrow(() -> new BussinessError(String.format("Не найден язык приложения: [%s]", language)));
            selectLanguage.click();
        }
        fieldScenarioSteps.clickField("События");
        AbstractPageObject.setCurrentPage(EventsPage.class);
    }

    public enum Language{

    }
}
