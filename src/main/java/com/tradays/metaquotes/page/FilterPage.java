package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.field.CheckBox;
import com.tradays.metaquotes.core.field.StaticText;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.CollectionPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FilterPage extends AbstractPageObject {

    @FieldName("Отметить все важные")
    @AndroidFindBy(xpath = ".//*[@text='IMPORTANCE']//following-sibling::*[@resource-id='net.metaquotes.economiccalendar:id/select_all']")
    private Button importanceSelectAll;

    @FieldName("Отметить все регионы")
    @AndroidFindBy(xpath = ".//*[@text='COUNTRY']//following-sibling::*[@resource-id='net.metaquotes.economiccalendar:id/select_all']")
    private Button countrySelectAll;

    @FieldName("Вернуться назад")
    @AndroidFindBy(accessibility = "Переход вверх")
    private Button previousPage;

    @FieldName("Список элементов фильтра")
    @AndroidFindBy(xpath = ".//*[@resource-id='net.metaquotes.economiccalendar:id/settings_list']/android.widget.LinearLayout[.//*[@resource-id='net.metaquotes.economiccalendar:id/check']]")
    private List<FilterItem> filterItems;

    @Override
    public boolean isLoaded() {
        return previousPage.isDisplayed();
    }

    public static class FilterItem extends CollectionPageObject {

        public FilterItem(SearchContext searchContext){
            super(searchContext);
        }

        @FieldName("Наименование")
        @AndroidFindBy(id = "title")
        private StaticText title;

        @FieldName("Чекбокс")
        @AndroidFindBy(id = "check")
        private CheckBox checkbox;
    }
}
