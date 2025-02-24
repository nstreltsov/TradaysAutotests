package com.tradays.metaquotes.core.driver;

import com.tradays.metaquotes.core.configuration.FrameworkConfig;
import com.tradays.metaquotes.core.configuration.IFrameworkConfig;
import com.tradays.metaquotes.core.exceptions.DriverCreationError;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Singleton класс создающий экземпляр драйвера
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
@Slf4j
public class MobileDriverFacade {

    private static MobileDriverFacade mobileDriverFacade;

    private AndroidDriver driver;

    private MobileDriverFacade(){
        driver = initDriver();
    }

    public static WebDriver getDriver(){
        if (Objects.isNull(mobileDriverFacade)) {
            mobileDriverFacade = new MobileDriverFacade();
        }
        return mobileDriverFacade.driver;
    }

    private AndroidDriver initDriver(){
        try {
            return new AndroidDriver(new URL(FrameworkConfig.get().appiumServer()), getCapabilities());
        } catch (MalformedURLException e) {
            throw new DriverCreationError("Не удалось создать драйвер", e);
        }
    }

    private DesiredCapabilities getCapabilities(){
        IFrameworkConfig frameworkConfig = FrameworkConfig.get();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, frameworkConfig.platform());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, frameworkConfig.platformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, frameworkConfig.device());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.APP, this.getClass().getClassLoader().getResource(frameworkConfig.application()));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, frameworkConfig.appPackage());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, frameworkConfig.appActivity());
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
        return capabilities;
    }
}
