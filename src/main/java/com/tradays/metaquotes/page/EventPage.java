package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.annotation.Page;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.field.StaticText;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.CollectionPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Page("Событие")
public class EventPage extends AbstractPageObject {

    @FieldName("Страна")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/country_name")
    private StaticText country;

    @FieldName("Важность")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/event_importance")
    private StaticText importance;

    @FieldName("История")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/tab_history")
    private Button history;

    @FieldName("История индекса потребительского доверия")
    @AndroidFindBy(xpath = "//*[@resource-id='net.metaquotes.economiccalendar:id/history_table']//*[@resource-id='net.metaquotes.economiccalendar:id/content']/android.widget.FrameLayout[.//android.widget.TextView]")
    private List<HistoryItem> eventItems;

    @Override
    public boolean isLoaded() {
        return country.isDisplayed();
    }

    public static class HistoryItem extends CollectionPageObject {

        public HistoryItem(SearchContext searchContext) {
            super(searchContext);
        }

        @FieldName("Дата")
        @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/time")
        private StaticText time;

        @FieldName("Актуальное")
        @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/actual")
        private StaticText actual;

        @FieldName("Прогноз")
        @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/forecast")
        private StaticText forecast;

        @FieldName("Предыдущее")
        @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/previous")
        private StaticText previous;
    }
}
