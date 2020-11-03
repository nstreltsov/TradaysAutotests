package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.PageConverter;
import com.tradays.metaquotes.steps.scenario.FieldScenarioSteps;
import com.tradays.metaquotes.steps.scenario.PageScenarioSteps;
import cucumber.api.Transform;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

/**
 * @author Nikolay Streltsov on 02.11.2020
 */
public class FieldSteps {

    private FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();

    private PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();

    @Когда("^выполнено нажатие на \"([^\"]*)\"$")
    public void clickField(String fieldName){
        fieldScenarioSteps.clickField(fieldName);
    }

    @Тогда("^значение поля \"([^\"]*)\" равно \"([^\"]*)\"$")
    public void checkFieldValue(String fieldName, String expected){
        fieldScenarioSteps.checkFieldValue(fieldName, expected);
    }

    @Когда("^нажатием на кнопку \"([^\"]*)\" загружена страница \"([^\"]*)\"$")
    public void clickFieldAndPageLoaded(String fieldName, @Transform(PageConverter.class) Class<? extends IPageObject> pageClass){
        fieldScenarioSteps.clickField(fieldName);
        pageScenarioSteps.stepLoadedPage(pageClass);
    }
}
