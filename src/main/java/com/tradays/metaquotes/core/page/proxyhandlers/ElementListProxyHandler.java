package com.tradays.metaquotes.core.page.proxyhandlers;

import com.tradays.metaquotes.core.field.MobileElementFacade;
import com.tradays.metaquotes.core.page.PageFactoryUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public class ElementListProxyHandler<T extends MobileElementFacade> implements InvocationHandler {

    private final Class<T> elementClass;
    private final ElementLocator locator;
    private final String name;

    public ElementListProxyHandler(Class<T> elementClass, ElementLocator locator, String name) {
        this.elementClass = elementClass;
        this.locator = locator;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        List<T> elements = new LinkedList<>();
        int elementNumber = 0;
        for (WebElement element : locator.findElements()) {
            String newName = String.format("%s [%d]", name, elementNumber++);
            elements.add(PageFactoryUtils.createElement(elementClass, element, newName));
        }

        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
