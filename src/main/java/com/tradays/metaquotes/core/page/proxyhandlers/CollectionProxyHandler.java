package com.tradays.metaquotes.core.page.proxyhandlers;

import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.PageFactoryUtils;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Создает объект типа Proxy, которые будет инициализирован при обращении, в данном случае при получении элементов коллекции PageObject
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public class CollectionProxyHandler<T extends AbstractPageObject> implements InvocationHandler {

    private final Class<T> elementClass;
    private final ElementLocator locator;
    private final String name;

    public CollectionProxyHandler(Class<T> elementClass, ElementLocator locator, String name) {
        this.elementClass = elementClass;
        this.locator = locator;
        this.name = name;
    }

    /**
     * метод, который будет вызван при обращении к Proxy-объекту и вернет конкретный экземпляр proxy-объекта
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }
        List<T> elements = new LinkedList<>();
        locator.findElements().forEach(element -> {
            elements.add(PageFactoryUtils.newInstance(elementClass, element));
        });
        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}