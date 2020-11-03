package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.annotation.Page;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.field.RadioButton;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Page("Настройки")
public class SettingsPage extends AbstractPageObject {

    private BottomBar bottomBar;

    @FieldName("Выбранный язык")
    @AndroidFindBy(xpath = "(//*[@resource-id='net.metaquotes.economiccalendar:id/hint'])[2]")
    private Button language;

    @FieldName("Доступные языки")
    @AndroidFindBy(xpath = "//*[@resource-id='android:id/select_dialog_listview']//android.widget.CheckedTextView")
    private List<RadioButton> languages;

    @Override
    public boolean isLoaded() {
        return language.isDisplayed();
    }
}
