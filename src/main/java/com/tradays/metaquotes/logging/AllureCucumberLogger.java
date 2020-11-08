package com.tradays.metaquotes.logging;

import gherkin.formatter.model.Feature;
import io.qameta.allure.cucumberjvm.AllureCucumberJvm;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Nikolay Streltsov on 04.11.2020
 */
@Slf4j
public class AllureCucumberLogger extends AllureCucumberJvm {

    @Override
    public void feature(final Feature feature) {
        log.info("START TEST: {}",feature.getName());
        super.feature(feature);
    }
}
