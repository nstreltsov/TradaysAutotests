package com.tradays.metaquotes.cucumber;

import com.tradays.metaquotes.core.annotation.Page;
import com.tradays.metaquotes.core.configuration.FrameworkConfig;
import com.tradays.metaquotes.core.exceptions.NoSuchPageError;
import com.tradays.metaquotes.core.page.IPageObject;
import cucumber.api.Transformer;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.reflections.Reflections;

import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class PageConverter extends Transformer<Class<? extends IPageObject>> {

    @Override
    public Class<? extends IPageObject> transform(java.lang.String s) {
        return searchPageObjectClass(s);
    }

    private static Class<? extends IPageObject> searchPageObjectClass(String pageName){
        String pagePackage = FrameworkConfig.get().pageObjects();
        Reflections reflections = new Reflections(pagePackage);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Page.class);
        try {
            return (Class<? extends IPageObject>) annotated.stream().filter(a -> a.getAnnotation(Page.class).name().equals(pageName)).findFirst().get();
        }catch (NoSuchElementException e){
            throw new NoSuchPageError(String.format("Не найден класс с наименованием: [%s]", pageName));
        }
    }
}