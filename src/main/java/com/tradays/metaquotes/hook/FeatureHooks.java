package com.tradays.metaquotes.hook;

import com.tradays.metaquotes.core.driver.MobileDriverFacade;
import cucumber.api.java.After;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public class FeatureHooks {
    @After
    public void closeDriver(){
        MobileDriverFacade.getDriver().quit();
    }
}
