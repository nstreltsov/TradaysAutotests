package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class BottomBar extends AbstractPageObject {

    @FieldName("Календарь")
    @FindBy(id = "net.metaquotes.economiccalendar:id/bottom_bar_calendar")
    private Button calendar;

    @FieldName("Настройки")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/bottom_bar_settings")
    private Button settings;

    @Override
    public boolean isLoaded() {
        return calendar.isDisplayed();
    }
}
