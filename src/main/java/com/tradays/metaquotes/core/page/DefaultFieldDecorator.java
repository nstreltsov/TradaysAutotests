package com.tradays.metaquotes.core.page;

import com.tradays.metaquotes.core.field.MobileElementFacade;
import com.tradays.metaquotes.core.page.proxyhandlers.CollectionProxyHandler;
import com.tradays.metaquotes.core.page.proxyhandlers.ElementProxyHandler;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.DefaultElementByBuilder;
import org.junit.Assert;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.time.Duration;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class DefaultFieldDecorator implements FieldDecorator {

    SearchContext searchContext;

    public DefaultFieldDecorator(SearchContext searchContext){
        this.searchContext = searchContext;
    }

    public Object decorate(ClassLoader classLoader, Field field) {
        try {
            if (PageFactoryUtils.isCollectionElement(field)) {
                return decorateElementCollection(classLoader, field);
            }
            if (PageFactoryUtils.isElement(field)){
                return decorateWebElement(classLoader, field);
            }
            return null;
        } catch (ClassCastException e) {
            e.printStackTrace();
            Assert.fail("Не удалось создать прокси для элемента: " + field.getName());
            return null;
        }
    }

    private Object decorateElementCollection(ClassLoader loader, Field field) {
        AppiumElementLocatorFactory locator = new AppiumElementLocatorFactory(searchContext, Duration.ZERO, new DefaultElementByBuilder("android", "this.automation"));
        InvocationHandler handler = new CollectionProxyHandler<>(PageFactoryUtils.getGenericParameterClass(field), locator.createLocator(field), PageFactoryUtils.getElementName(field));
        return ProxyFactory.createElementCollectionProxy(loader, handler);
    }

    private  <T extends MobileElementFacade> T decorateWebElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = proxyWebElement(loader, field);
        return PageFactoryUtils.createElement((Class<T>) field.getType(), elementToWrap,field.getName());
    }

    private WebElement proxyWebElement(ClassLoader loader, Field field) {
        AppiumElementLocatorFactory locator = new AppiumElementLocatorFactory(searchContext, Duration.ZERO, new DefaultElementByBuilder("android", "this.automation"));
        InvocationHandler handler = new ElementProxyHandler(locator.createLocator(field), field.getName());
        return ProxyFactory.createWebElementProxy(loader, handler);
    }
}
