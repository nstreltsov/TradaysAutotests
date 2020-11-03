package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.annotation.Page;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Page("Нижнее меню")
public class BottomBar extends AbstractPageObject {

    @FieldName("События")
    @FindBy(id = "net.metaquotes.economiccalendar:id/bottom_bar_calendar")
    private Button events;

    @FieldName("Настройки")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/bottom_bar_settings")
    private Button settings;

    @Override
    public boolean isLoaded() {
        return events.isDisplayed();
    }
}
