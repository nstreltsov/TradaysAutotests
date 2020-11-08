package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.steps.scenario.FilterScenarioSteps;
import cucumber.api.java.ru.Когда;

import java.util.List;

/**
 * Содержит частные Cucumber шаги работы со страницей установки фильтров
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FilterSteps {

    FilterScenarioSteps filterScenarioSteps = new FilterScenarioSteps();

    @Когда("^установлен фильтр:$")
    public void stepSetFilter(List<FieldValueTable> conditions) {
        filterScenarioSteps.stepSetFilter(conditions);
    }
}
