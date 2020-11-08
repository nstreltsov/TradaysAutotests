package com.tradays.metaquotes.steps.feature;

import com.tradays.metaquotes.cucumber.VariableConverter;
import com.tradays.metaquotes.steps.scenario.LoggingScenarioSteps;
import cucumber.api.Transform;
import cucumber.api.java.ru.Когда;

/**
 * Содержит вспомогательные шаги для вывода значений в лог
 *
 * @author Nikolay Streltsov on 08.11.2020
 */
public class LoggingSteps {

    private LoggingScenarioSteps loggingScenarioSteps = new LoggingScenarioSteps();

    @Когда("^вывести в консоль значение переменной \"([^\"]*)\"")
    public void stepPrintVariableToConsole(@Transform(VariableConverter.class) Object variable){
        loggingScenarioSteps.printVariableToConsole(variable);
    }
}
