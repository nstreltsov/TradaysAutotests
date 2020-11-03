package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class SettingsPage extends AbstractPageObject {

    private BottomBar bottomBar;

    @FieldName("Выбранный язык")
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@clickable='true' and .//android.widget.TextView[@text='Language']]//android.widget.TextView[@resource-id='net.metaquotes.economiccalendar:id/hint']")
    private Button language;

    @Override
    public boolean isLoaded() {
        return false;
    }
}
