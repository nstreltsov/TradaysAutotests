package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.logging.StepListener;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 * @author Nikolay Streltsov on 02.11.2020
 */
@Slf4j
public class FieldScenarioSteps {

    @Step("выполнено нажатие на {fieldName}")
    public void clickField(String fieldName){
        IPageObject page = AbstractPageObject.getCurrentPage();
        page.getField(fieldName).click();
    }

    @Step("значение поля \"{fieldName}\" равно \"{expected}\"")
    public void checkFieldValue(String fieldName, String expected){
        String actual = getFieldValue(fieldName);
        Assert.assertEquals(String.format("Значение поля [%s] не соответствует ожидаемому [%s]", fieldName, expected), expected, actual);
    }

    @Step("получено значение поля \"{fieldName}\"")
    public String getFieldValue(String fieldName){
        IPageObject page = AbstractPageObject.getCurrentPage();
        String value = page.getField(fieldName).getText().trim();
        log.info("GET VALUE : {} значение поля [{}]: {}", StepListener.getPrefix(), fieldName, value);
        return value;
    }

}
