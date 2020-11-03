package com.tradays.metaquotes.core.page;

import com.tradays.metaquotes.core.field.MobileElementFacade;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public interface IPageObject {

    <T extends MobileElementFacade> T getField(String name);

    <T extends IPageObject> List<T> getCollection(String name);

    <T extends MobileElementFacade> List<T> getFields(String name);

    SearchContext getSearchContext();

    boolean isLoaded();
}
