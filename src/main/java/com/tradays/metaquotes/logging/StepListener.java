package com.tradays.metaquotes.logging;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Логирование выполнения шагов теста
 *
 * @author Nikolay Streltsov on 07.11.2020
 */
@Slf4j
public class StepListener implements StepLifecycleListener {

    //контейнер для определения сколько шагов выполняется в текущий момент, необходим для определения табуляции в отчете
    //TODO для многопоточного запуска переделать на ThreadLocal
    private static LinkedList<StepResult> stepResults = new LinkedList<>();

    //отступ для вложенного шага
    private static String prefix = "      ";

    @Override
    public void afterStepStart(StepResult result) {
        log.info("START STEP: {}", getPrefix() + result.getName());
        stepResults.add(result);
    }

    @Override
    public void beforeStepStop(StepResult result) {
        stepResults.remove(result);
        log.info("STOP STEP:  {} STATUS: {}", getPrefix() + result.getName(), result.getStatus());
    }

    /**
     * опеределяет сколько отступов в логе необходимо для текущего шага
     * @return - отсуп для шага
     */
    public static String getPrefix() {
        AtomicReference<String> prefixAtomicReference = new AtomicReference<>("");
        stepResults.forEach(stepResult -> prefixAtomicReference.set(prefixAtomicReference + prefix));
        return prefixAtomicReference.get();
    }
}
