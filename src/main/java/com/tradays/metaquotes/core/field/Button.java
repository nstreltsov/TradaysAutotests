package com.tradays.metaquotes.core.field;

import com.tradays.metaquotes.core.driver.MobileDriverFacade;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class Button extends MobileElementFacade{

    public Button(WebElement wrappedElement, String elementName) {
        super(wrappedElement, elementName);
    }

    @Override
    public void click() {
        Point p1 = super.getRect().getPoint();
        new AndroidTouchAction((PerformsTouchActions) MobileDriverFacade.getDriver()).tap(PointOption.point(p1.x, p1.y)).perform();
    }

    @Override
    public void type(String value){
        throw new UnsupportedOperationException(String.format("Объект [%s] не поддеривает операцию присваивания значения", super.elementName));
    }

}
