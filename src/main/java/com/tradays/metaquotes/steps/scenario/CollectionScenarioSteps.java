package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.driver.MobileDriverFacade;
import com.tradays.metaquotes.core.field.MobileElementFacade;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.variable.TestVariableHolder;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
@Slf4j
public class CollectionScenarioSteps {

    private FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();
    private PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();

    @Step("выбирается элемент коллекции \"{collectionName}\" с параметрами: \"$conditions\"")
    public IPageObject stepSetCollectionByConditions(String collectionName, List<FieldValueTable> conditions) {
        return AbstractPageObject.setCurrentPage(searchItem(collectionName, conditions));
    }

    @Step("в переменной \"{variable}\" сохранены значения \"{count}\" элементов \"{collectionName}\" с параметрами: \"$conditions\"")
    public void stepGetCollectionValuesByConditions(String variable, String collectionName, List<String> conditions, int count) {
        IPageObject collectionPage = AbstractPageObject.getCurrentPage();
        List<String> actualValues = new ArrayList<>();
        actualValues.add(String.join("|", conditions));
        actualValues.addAll(getCollectionValuesByConditionsRecursive(collectionPage, collectionName, conditions, count, new ArrayList<>()));
        TestVariableHolder.getVariables().put(variable, actualValues);
    }

    private List<String> getCollectionValuesByConditionsRecursive(
            IPageObject collectionPage,
            String collectionName,
            List<String> fields,
            int expectedCount,
            List<String> actualValues){
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection(collectionName);
        for (IPageObject item: items) {
            String value = "";
            if (actualValues.size() == expectedCount){
                return actualValues;
            }
            for (String field: fields) {
                AbstractPageObject.setCurrentPage(item);
                String fieldValue = fieldScenarioSteps.getFieldValue(field);
                if (value.isEmpty()){
                    value = fieldValue;
                }else{
                    value = String.join("|", value, fieldValue);
                }
            }
            actualValues.add(value);
        }
        if (actualValues.size() < expectedCount){
            AbstractPageObject.verticalScrollToTop((WebElement)items.get(items.size() - 1).getSearchContext());
            AbstractPageObject.setCurrentPage(collectionPage);
            return getCollectionValuesByConditionsRecursive(collectionPage, collectionName, fields, expectedCount, actualValues);
        }
        return actualValues;
    }
    @Step
    private IPageObject searchItem(String collectionName, List<FieldValueTable> conditions) {
        IPageObject found = itemExist(collectionName, conditions);
        Assert.assertNotNull(String.format("Не найден элемент коллекции \"[%s]\" с параметрами: \"[%s]\"", collectionName, conditions.toString()), found);
        return found;
    }

    @Step
    private IPageObject itemExist(String collectionName, List<FieldValueTable> conditions) {
        String pageSource = MobileDriverFacade.getDriver().getPageSource();
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
                log.debug(actual);
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
            AbstractPageObject.verticalScroll((WebElement)items.get(items.size() - 1).getSearchContext(), (WebElement)items.get(0).getSearchContext());
            String currentPageSource = MobileDriverFacade.getDriver().getPageSource();
            if (pageSource.equals(currentPageSource)){
                return found;
            }else{
                return itemExist(collectionName, conditions);
            }
        }
        return found;
    }


    @Step("выбирается элемент коллекции \"{collectionName}\" с индексом \"{index}\"")
    public void stepSetCollectionByIndex(String collectionName, int index){
        AbstractPageObject.setCurrentPage(searchItemByIndex(collectionName, index));
    }

    @Step
    public IPageObject searchItemByIndex(String collectionName, int index) {
        IPageObject page = AbstractPageObject.getCurrentPage();
        List<IPageObject> items = page.getCollection(collectionName);
        if (items.size() < index + 1) {
            Assert.fail(String.format("В списке [%s] нет элемента с индексом [%s]", collectionName, index));
        }
        return items.get(index);
    }
}
