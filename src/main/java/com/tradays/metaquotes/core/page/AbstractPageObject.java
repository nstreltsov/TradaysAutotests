package com.tradays.metaquotes.core.page;

import com.tradays.metaquotes.core.driver.WebDriverFacade;
import com.tradays.metaquotes.core.exceptions.ElementCreationError;
import com.tradays.metaquotes.core.field.MobileElementFacade;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public abstract class AbstractPageObject implements IPageObject{

    private static IPageObject currentPage;

    @Getter
    private SearchContext searchContext;

    public AbstractPageObject(){
        this(WebDriverFacade.getDriver());
    }

    public AbstractPageObject(SearchContext searchContext){
        this.searchContext = searchContext;
        PageFactory.initElements(new DefaultFieldDecorator(searchContext), this);
    }

    public static IPageObject getCurrentPage() {
        return currentPage;
    }

    public abstract boolean isLoaded();

    @Override
    public <T extends MobileElementFacade> T getField(String name){
        MobileElementFacade element = searchField(this, name);
        if (Objects.nonNull(element)){
            return (T) element;
        }else{
            throw new ElementCreationError(String.format("В классе [%s] не найден элемент [%s]", this, name));
        }
    }

    @Override
    public <T extends MobileElementFacade> List<T> getFields(String name){
        return null;
    }

    @Override
    public <T extends IPageObject> List<T> getCollection(String name) {
        List<IPageObject> element = searchCollection(this, name);
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


    public <T extends MobileElementFacade> T searchField(AbstractPageObject pageObject, String name){
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

    private <T extends MobileElementFacade> T searchFieldThisClass(AbstractPageObject pageObject, String name){
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

    public <T extends IPageObject> List<T> searchCollection(AbstractPageObject pageObject, String name){
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

    private <T extends IPageObject> List<T> searchCollectionThisClass(AbstractPageObject pageObject, String name){
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
