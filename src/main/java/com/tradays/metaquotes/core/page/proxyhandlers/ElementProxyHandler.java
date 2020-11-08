package com.tradays.metaquotes.core.page.proxyhandlers;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import java.lang.reflect.Method;

/**
 * Создает объект типа Proxy, который будет инициализирован при обращении, в данном случае (Button, Checkbox и т.д.)
 *
 * @author Nikolay Streltsov on 01.11.2020
 */
public class ElementProxyHandler extends LocatingElementHandler {

    private final String name;

    public ElementProxyHandler(ElementLocator locator, String name) {
        super(locator);
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
        return super.invoke(o, method, objects);
    }
}
