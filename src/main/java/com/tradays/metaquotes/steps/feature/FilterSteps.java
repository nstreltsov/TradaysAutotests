package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.steps.scenario.FilterScenarioSteps;
import cucumber.api.java.ru.Когда;
import io.qameta.allure.Step;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FilterSteps {

    FilterScenarioSteps filterScenarioSteps = new FilterScenarioSteps();

    @Когда("^Установлен фильтр:$")
    public void stepSetFilter(List<FieldValueTable> conditions) {
        filterScenarioSteps.stepSetFilter(conditions);
    }
}
