package com.tradays.metaquotes.page;

import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.annotation.Page;
import com.tradays.metaquotes.core.field.Button;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.CollectionPageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Page("События")
public class EventsPage extends AbstractPageObject {

    private BottomBar bottomBar;

    @FieldName("Фильтр")
    @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/menu_filter")
    private Button filter;

    @FieldName("Список событий")
    @AndroidFindBy(xpath = "//*[@resource-id='net.metaquotes.economiccalendar:id/events_list']/android.widget.FrameLayout")
    private List<EventItem> eventItems;

    @Override
    public boolean isLoaded() {
        boolean loaded = filter.isDisplayed();
        waitInvisibilityLoader();
        return loaded;
    }

    public static class EventItem extends CollectionPageObject {

        public EventItem(SearchContext searchContext) {
            super(searchContext);
        }

        @FieldName("Заголовок")
        @AndroidFindBy(id = "net.metaquotes.economiccalendar:id/title")
        private Button title;
    }
}
