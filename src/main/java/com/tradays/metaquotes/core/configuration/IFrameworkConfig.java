package com.tradays.metaquotes.core.configuration;

import org.aeonbits.owner.Config;

/**
 * Описываются поля файла конфигурации environment.properties
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public interface IFrameworkConfig extends Config {

    @Key("driver.timeouts.implicitlywait")
    @DefaultValue("10")
    long implicitlywait();

    @Key("page.timeouts.wait")
    @DefaultValue("10")
    long pageLoadedWait();

    @Key("page.objects")
    String pageObjects();

    @Key("device")
    String device();

    @Key("platform")
    String platform();

    @Key("platformVersion")
    String platformVersion();

    @Key("appium.server")
    String appiumServer();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

}
