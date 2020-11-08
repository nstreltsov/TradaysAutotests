package com.tradays.metaquotes.core.page;

import com.google.common.collect.Lists;
import com.tradays.metaquotes.core.annotation.FieldName;
import com.tradays.metaquotes.core.exceptions.CreatePageError;
import com.tradays.metaquotes.core.exceptions.ElementCreationError;
import com.tradays.metaquotes.core.field.MobileElementFacade;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

/**
 * Вспомошательный класс позволяющий определять типа полей класса и создавать объекты класса
 * 
 * @author Nikolay Streltsov on 16.05.2020
 */
public class PageFactoryUtils {

    public static boolean isElement(Field field) {
        return !field.getType().isAssignableFrom(List.class) && isElement(field.getType());
    }

    public static boolean isCollectionElement(Field field) {
        return field.getType().isAssignableFrom(List.class) && ICollectionPageObject.class.isAssignableFrom(getGenericParameterClass(field));

    }

    public static boolean isListElement(Field field) {
        return field.getType().isAssignableFrom(List.class) && MobileElementFacade.class.isAssignableFrom(getGenericParameterClass(field));
    }

    public static boolean isElement(Class<?> clazz) {
        return MobileElementFacade.class.isAssignableFrom(clazz);
    }


    public static <T extends MobileElementFacade> T createElement(Class<T> elementClass, WebElement elementToWrap,
                                                               String elementName) {
        try {
            T instance = newInstance(elementClass, elementToWrap, elementName);
            return instance;
        } catch (Exception e) {
            throw new ElementCreationError(e);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Object... args) {
        try {
            if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
                Class outerClass = clazz.getDeclaringClass();
                Object outerObject = outerClass.newInstance();
                return invokeConstructor(clazz, Lists.asList(outerObject, args).toArray());
            }
            return invokeConstructor(clazz, args);
        }catch (Exception e){
            throw new CreatePageError(String.format("Не удалось создать экземпляр класса [%s]", clazz));
        }

    }

    public static Class getGenericParameterClass(Field field) {
        Type genericType = field.getGenericType();
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    public static String getElementName(Field field){
        if(field.isAnnotationPresent(FieldName.class)){
            FieldName element = field.getAnnotation(FieldName.class);
            return element.value();
        }
        return "";
    }
}
