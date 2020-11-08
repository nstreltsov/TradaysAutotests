package com.tradays.metaquotes.core.page;

import com.tradays.metaquotes.core.exceptions.ElementCreationError;
import com.tradays.metaquotes.core.field.MobileElementFacade;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Вспомогательный класс для поиска полей внутри классов по их наименованию указанному в аннотации @FieldName
 *
 * @author Nikolay Streltsov on 03.11.2020
 */
public class SearchFieldUtils {

    public static <T extends MobileElementFacade> T searchField(AbstractPageObject pageObject, String name){
        for (Field field: pageObject.getClass().getDeclaredFields()){
            MobileElementFacade element = searchFieldThisClass(pageObject, name);
            if (Objects.nonNull(element)){
                return (T) element;
            }
            if (AbstractPageObject.class.isAssignableFrom(field.getType())){
                try {
                    return searchField((AbstractPageObject) field.getType().getConstructor().newInstance(), name);
                } catch (Exception e) {
                    throw new ElementCreationError(String.format("Не удалось создать объект [%s]", field.getType()));
                }
            }
        }
        return null;
    }

    private static <T extends MobileElementFacade> T searchFieldThisClass(AbstractPageObject pageObject, String name){
        Field fieldElement = Arrays.asList(pageObject.getClass().getDeclaredFields()).stream()
                .filter(field -> PageFactoryUtils.getElementName(field).equals(name)).findAny().orElse(null);
        if (Objects.nonNull(fieldElement)){
            fieldElement.setAccessible(true);
            try {
                return (T) fieldElement.get(pageObject);
            } catch (IllegalAccessException e) {
                throw new ElementCreationError(String.format("Не удалось создать объект поля [%s]", name));
            }
        }else{
            return null;
        }
    }

    public static <T extends MobileElementFacade> List<T> searchFields(AbstractPageObject pageObject, String name){
        for (Field field: pageObject.getClass().getDeclaredFields()){
            List<MobileElementFacade> element = searchFieldsThisClass(pageObject, name);
            if (Objects.nonNull(element)){
                return (List<T>) element;
            }
            if (AbstractPageObject.class.isAssignableFrom(field.getType())){
                try {
                    return searchField((AbstractPageObject) field.getType().getConstructor().newInstance(), name);
                } catch (Exception e) {
                    throw new ElementCreationError(String.format("Не удалось создать объект [%s]", field.getType()));
                }
            }
        }
        return null;
    }

    private static <T extends MobileElementFacade> List<T> searchFieldsThisClass(AbstractPageObject pageObject, String name){
        Field fieldElement = Arrays.asList(pageObject.getClass().getDeclaredFields()).stream()
                .filter(field -> PageFactoryUtils.getElementName(field).equals(name)).findAny().orElse(null);
        if (Objects.nonNull(fieldElement)){
            fieldElement.setAccessible(true);
            try {
                return (List<T>) fieldElement.get(pageObject);
            } catch (IllegalAccessException e) {
                throw new ElementCreationError(String.format("Не удалось создать объект поля [%s]", name));
            }
        }else{
            return null;
        }
    }
    public static <T extends IPageObject> List<T> searchCollection(AbstractPageObject pageObject, String name){
        for (Field field: pageObject.getClass().getDeclaredFields()){
            List<IPageObject> collection = searchCollectionThisClass(pageObject, name);
            if (Objects.nonNull(collection)){
                return (List<T>) collection;
            }
            if (AbstractPageObject.class.isAssignableFrom(field.getType())){
                try {
                    return searchCollection((AbstractPageObject) field.getType().getConstructor().newInstance(), name);
                } catch (Exception e) {
                    throw new ElementCreationError(String.format("Не удалось создать объект [%s]", field.getType()));
                }
            }
        }
        return null;
    }

    private static <T extends IPageObject> List<T> searchCollectionThisClass(AbstractPageObject pageObject, String name){
        Field fieldElement = Arrays.asList(pageObject.getClass().getDeclaredFields()).stream()
                .filter(field -> PageFactoryUtils.getElementName(field).equals(name)).findAny().orElse(null);
        if (Objects.nonNull(fieldElement)){
            fieldElement.setAccessible(true);
            try {
                return (List<T>) fieldElement.get(pageObject);
            } catch (IllegalAccessException e) {
                throw new ElementCreationError(String.format("Не удалось создать объект поля [%s]", name));
            }
        }else{
            return null;
        }
    }
}
