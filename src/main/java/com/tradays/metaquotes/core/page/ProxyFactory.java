package com.tradays.metaquotes.core.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Фабрика, создающая Proxy-объекты для:
 *      Элементов (Button, RadioButton и т.д.)
 *      Списка элементов  (List<Button>, List<Checkbox> и т.д.)
 *      Списка коллекций (List<T extends ICollectionPageObject>>)
 * @author Nikolay Streltsov on 01.11.2020
 */
public class ProxyFactory {

    public static <T extends WebElement> T createWebElementProxy(ClassLoader loader, InvocationHandler handler) {
        Class<?>[] interfaces = new Class[]{WebElement.class, WrapsElement.class};
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }
    public static <T extends AbstractPageObject> List<T> createElementCollectionProxy(ClassLoader loader, InvocationHandler handler) {
        return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
    public static <T extends WebElement> List<T> creatElementListProxy(ClassLoader loader, InvocationHandler handler) {
        return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
