package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.steps.scenario.CollectionScenarioSteps;
import cucumber.api.java.ru.Когда;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class CollectionSteps {

    private CollectionScenarioSteps collectionScenarioSteps = new CollectionScenarioSteps();

    @Когда("^выбран элемент коллекции \"([^\"]*)\" с параметрами:$")
    public void stepSetCollectionByConditions(String collectionName, List<FieldValueTable> conditions){
        collectionScenarioSteps.stepSetCollectionByConditions(collectionName, conditions);
    }
}
