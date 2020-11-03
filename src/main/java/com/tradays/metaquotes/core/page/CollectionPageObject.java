package com.tradays.metaquotes.core.page;

import org.openqa.selenium.SearchContext;

/**
 * @author Nikolay Streltsov on 17.05.2020
 */
public abstract class CollectionPageObject extends AbstractPageObject implements ICollectionPageObject{

    public CollectionPageObject(){}

    public CollectionPageObject(SearchContext searchContext){
        super(searchContext);
    }

    @Override
    public boolean isLoaded() {
        return true;
    }
}
