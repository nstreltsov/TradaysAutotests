package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.VariableConverter;
import com.tradays.metaquotes.steps.scenario.LoggingScenarioSteps;
import cucumber.api.Transform;
import cucumber.api.java.ru.Когда;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Streltsov on 08.11.2020
 */
public class LoggingSteps {

    private LoggingScenarioSteps loggingScenarioSteps = new LoggingScenarioSteps();

    @Когда("^вывести в консоль значение переменной \"([^\"]*)\"")
    public void stepPrintVariableToConsole(@Transform(VariableConverter.class) Object variable){
        loggingScenarioSteps.printVariableToConsole(variable);
    }

    @Когда("debug")
    public void debud(){
        List<String> list = new ArrayList<>();
        list.add("Дата|Актуальное|Прогноз");
        list.add("10 jan 2019|2,5 %|1,5 %");
        list.add("10 jan 2020|2,5 %|43,5 %");
        list.add("1 aug 2019|22,5 %|3,5 %");
        loggingScenarioSteps.printVariableToConsole(list);
    }
}
