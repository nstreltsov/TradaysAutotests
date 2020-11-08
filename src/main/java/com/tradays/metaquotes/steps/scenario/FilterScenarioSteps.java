package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.field.MobileElementFacade;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.page.EventsPage;
import com.tradays.metaquotes.page.FilterPage;
import io.qameta.allure.Step;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FilterScenarioSteps {

    private PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();

    private CollectionScenarioSteps collectionScenarioSteps = new CollectionScenarioSteps();

    private FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();

    private final String collectionName = "Список элементов фильтра";

    @Step("устанавливается фильтр: \"{conditions}\"")
    public void stepSetFilter(List<FieldValueTable> conditions) {
        fieldScenarioSteps.clickField("Фильтр");
        pageScenarioSteps.stepLoadedPage(FilterPage.class);
        unselectedAll();
        conditions.forEach(condition -> {
            pageScenarioSteps.stepLoadedPage(FilterPage.class);
            IPageObject item = collectionScenarioSteps.stepSetCollectionByConditions(collectionName, Arrays.asList(condition));
            item.getField("Чекбокс").click();
        });
        AbstractPageObject.setCurrentPage(FilterPage.class);
        fieldScenarioSteps.clickField("Вернуться назад");
        AbstractPageObject.setCurrentPage(EventsPage.class);
    }

    @Step
    private void unselectedAll(){
        unselectedImportance();
        unselectedCountry();
    }

    private void unselectedImportance(){
        fieldScenarioSteps.clickField("Отметить все важные");
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection(collectionName);
        MobileElementFacade checkbox = items.get(0).getField("Чекбокс");
        if (checkbox.isSelected()){
            fieldScenarioSteps.clickField("Отметить все важные");
        }
    }

    private void unselectedCountry(){
        fieldScenarioSteps.clickField("Отметить все регионы");
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection(collectionName);
        MobileElementFacade checkbox = items.get(4).getField("Чекбокс");
        if (checkbox.isSelected()){
            fieldScenarioSteps.clickField("Отметить все регионы");
        }
    }
}
