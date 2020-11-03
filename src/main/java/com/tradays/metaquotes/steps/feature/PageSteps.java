package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.PageConverter;
import com.tradays.metaquotes.steps.scenario.PageScenarioSteps;
import cucumber.api.Transform;
import cucumber.api.java.ru.Тогда;
import io.qameta.allure.Step;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class PageSteps {

    private PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();

    @Тогда("^страница \"([^\"]*)\" загружена$")
    public void stepLoadedPage(@Transform(PageConverter.class) Class<? extends IPageObject> pageClass){
        pageScenarioSteps.stepLoadedPage(pageClass);
    }
}
