package com.tradays.metaquotes.core.field;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Паттерн Facade надо классом WebElement для расширения функциональности и
 * переопределения методов взаимодействия с приложением в классах наследниках(конкретных типах элементов, например Button, Checkbox и т.д.)
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public abstract class MobileElementFacade implements WrapsElement, WebElement {

    private final WebElement wrappedElement;

    protected final String elementName;

    public MobileElementFacade(WebElement wrappedElement, String elementName) {
        this.wrappedElement = wrappedElement;
        this.elementName = elementName;
    }

    public boolean exists() {
        try {
            getWrappedElement().isDisplayed();
        } catch (NoSuchElementException ignored) {
            return false;
        }
        return true;
    }

    public void type(String value) {
        sendKeys(value);
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    @Override
    public void click() {
        getWrappedElement().click();
    }

    @Override
    public void submit() {
        getWrappedElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getWrappedElement().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        getWrappedElement().clear();
    }

    @Override
    public String getTagName() {
        return getWrappedElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getWrappedElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return getWrappedElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWrappedElement().isEnabled();
    }

    @Override
    public String getText() {
        return getWrappedElement().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWrappedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWrappedElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getWrappedElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getWrappedElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWrappedElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWrappedElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWrappedElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        return getWrappedElement().getScreenshotAs(target);
    }
}
