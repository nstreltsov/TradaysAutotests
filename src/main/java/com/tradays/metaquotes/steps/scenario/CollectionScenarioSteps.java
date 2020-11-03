package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.driver.WebDriverFacade;
import com.tradays.metaquotes.core.field.MobileElementFacade;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.FieldTable;
import com.tradays.metaquotes.cucumber.FieldValueTable;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Slf4j
public class CollectionScenarioSteps {

    private FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();
    private PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();

    @Step("выбирается элемент коллекции \"$collectionName\" с параметрами: \"$conditions\"")
    public IPageObject stepSetCollectionByConditions(String collectionName, List<FieldValueTable> conditions) {
        return AbstractPageObject.setCurrentPage(searchItem(collectionName, conditions));
    }

    @Step("получены значения \"$count\" элементов \"$collectionName\" с параметрами: \"$conditions\"")
    public void stepGetCollectionValuesByConditions(String collectionName, List<FieldTable> conditions, int count) {
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection(collectionName);
        int currentItemsSize = items.size();
        items.forEach(item -> {
            conditions.forEach(field -> {
                AbstractPageObject.setCurrentPage(item);
                System.err.println(fieldScenarioSteps.getFieldValue(field.getField()));
            });
        });
        //TODO добавить скролл, если элементов меньше запрашиваемого
    }

    @Step
    private IPageObject searchItem(String collectionName, List<FieldValueTable> conditions) {
        IPageObject found = itemExist(collectionName, conditions);
        Assert.assertNotNull(String.format("Не найден элемент коллекции \"[%s]\" с параметрами: \"[%s]\"", collectionName, conditions.toString()), found);
        return found;
    }

    @Step
    private IPageObject itemExist(String collectionName, List<FieldValueTable> conditions) {
        String pageSource = WebDriverFacade.getDriver().getPageSource();
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection(collectionName);
        IPageObject found = null;
        for (IPageObject item : items) {
            boolean match = true;
            for (FieldValueTable condition: conditions) {
                String expected = condition.getValue();
                MobileElementFacade elItem = item.getField(condition.getField());
                String actual = elItem.getText();
                if (actual == null)
                    actual = "";
                log.info(actual);
                if (!StringUtils.equals(expected, actual)) {
                    match = false;
                }
            }
            if (match) {
                found = item;
                break;
            }
        }
        if (Objects.isNull(found)){
            verticalScroll((WebElement)items.get(items.size() - 1).getSearchContext(), (WebElement)items.get(0).getSearchContext());
            String currentPageSource = WebDriverFacade.getDriver().getPageSource();
            if (pageSource.equals(currentPageSource)){
                return found;
            }else{
                return itemExist(collectionName, conditions);
            }
        }
        return found;
    }

    private void verticalScroll(WebElement elementStart, WebElement elementEnd) {
        Point p1 = elementStart.getRect().getPoint();
        Point p2 = elementEnd.getRect().getPoint();
        new AndroidTouchAction((PerformsTouchActions) WebDriverFacade.getDriver())
                .press(PointOption.point(p1.x, p1.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
                .moveTo(PointOption.point(p2.x, p2.y)).release().perform();
    }
}
