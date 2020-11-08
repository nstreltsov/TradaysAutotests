package com.tradays.metaquotes.steps.scenario;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikolay Streltsov on 08.11.2020
 */
@Slf4j
public class LoggingScenarioSteps {

    public void printVariableToConsole(Object variable){
        if (variable instanceof List){
            String tableFormat = getStringFormat((List<String>) variable);
            String consoleOut = ((List<String>) variable).stream().map(o -> String.format(tableFormat + System.lineSeparator(), o.split("\\|"))).collect(Collectors.joining(""));
            log.info("значение перенной: {}{}", System.lineSeparator(), consoleOut);
        }else{
            log.info(String.valueOf(variable));
        }
    }

    /**
     * Определяет StringFormat c оптимальной шириной столбцов
     *
     * @param variables
     * @return StringFormat, например |%-15s|%-15s|%-15s|
     */
    private String getStringFormat(List<String> variables){
        String stringFormat = "";
        int lineCount = variables.size();
        if (lineCount == 0){
            return stringFormat;
        }
        int columnCount = variables.get(0).split("\\|").length;
        for (int i = 0; i < columnCount; i++) {
            int finalI = i;
            Integer wordsMax = variables.stream().map(line -> line.split("\\|")[finalI]).map(String::length).max(Integer::compareTo).get();
            stringFormat = String.join("|", stringFormat, "%-" + wordsMax + "s");
        }
        return stringFormat + "|";
    }
}
