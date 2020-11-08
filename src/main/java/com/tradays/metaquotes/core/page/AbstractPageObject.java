package com.tradays.metaquotes.core.page;

import com.tradays.metaquotes.core.configuration.FrameworkConfig;
import com.tradays.metaquotes.core.driver.MobileDriverFacade;
import com.tradays.metaquotes.core.exceptions.ElementCreationError;
import com.tradays.metaquotes.core.field.MobileElementFacade;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Основной класс иерарзии PageObject
 * От этого класса должны наследоваться конкретные реализации PageObject
 * Содержит методы поиска элементов внутри конкретных PageObject(необходимо чтобы искать поля по аннотации FieldName)
 * Переопределяет стандартный декоратор элементов PageObject для установки конкретного типа WebElement и возможности работы с коллекциями полей
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public abstract class AbstractPageObject implements IPageObject{

    private static IPageObject currentPage;

    @Getter
    private SearchContext searchContext;

    public AbstractPageObject(){
        this(MobileDriverFacade.getDriver());
    }

    public AbstractPageObject(SearchContext searchContext){
        this.searchContext = searchContext;
        PageFactory.initElements(new DefaultFieldDecorator(searchContext), this);
    }

    public static IPageObject getCurrentPage() {
        return currentPage;
    }

    public abstract boolean isLoaded();

    protected void waitInvisibilityLoader() {
        WebDriverWait wait = new WebDriverWait(MobileDriverFacade.getDriver(), FrameworkConfig.get().pageLoadedWait());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@resource-id='net.metaquotes.economiccalendar:id/loader']")));
    }

        @Override
    public <T extends MobileElementFacade> T getField(String name){
        MobileElementFacade element = SearchFieldUtils.searchField(this, name);
        if (Objects.nonNull(element)){
            return (T) element;
        }else{
            throw new ElementCreationError(String.format("В классе [%s] не найден элемент [%s]", this, name));
        }
    }

    @Override
    public <T extends MobileElementFacade> List<T> getFields(String name){
        List<MobileElementFacade> element = SearchFieldUtils.searchFields(this, name);
        if (Objects.nonNull(element)){
            return (List<T>) element;
        }else{
            throw new ElementCreationError(String.format("В классе [%s] не найден элемент [%s]", this, name));
        }
    }

    @Override
    public <T extends IPageObject> List<T> getCollection(String name) {
        List<IPageObject> element = SearchFieldUtils.searchCollection(this, name);
        if (Objects.nonNull(element)){
            return (List<T>) element;
        }else{
            throw new ElementCreationError(String.format("В классе [%s] не найдена коллекция [%s]", this, name));
        }
    }

    public static IPageObject setCurrentPage(Class<? extends IPageObject> pageClass){
        currentPage = PageFactoryUtils.newInstance(pageClass);
        return currentPage;
    }
    public static IPageObject setCurrentPage(IPageObject pageObject){
        currentPage = pageObject;
        return currentPage;
    }


    public static void verticalScroll(WebElement elementStart, WebElement elementEnd) {
        Point p1 = elementStart.getRect().getPoint();
        Point p2 = elementEnd.getRect().getPoint();
        new AndroidTouchAction((PerformsTouchActions) MobileDriverFacade.getDriver())
                .press(PointOption.point(p1.x, p1.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
                .moveTo(PointOption.point(p2.x, p2.y)).release().perform();
    }

    public static void verticalScrollToTop(WebElement elementStart) {
        verticalScroll(elementStart,
                MobileDriverFacade.getDriver().findElement(By.xpath(".//*[@resource-id='net.metaquotes.economiccalendar:id/main_toolbar']")));
    }
}
