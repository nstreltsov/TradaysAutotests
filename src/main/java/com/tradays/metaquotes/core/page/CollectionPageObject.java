package com.tradays.metaquotes.core.page;

import org.openqa.selenium.SearchContext;

/**
 * Родительский класс для работы с коллекциями (одинаковыми элементами страницы)
 * Позволяет искать элементы относительно других элементов
 *
 * @author Nikolay Streltsov on 17.05.2020
 */
public abstract class CollectionPageObject extends AbstractPageObject implements ICollectionPageObject{

    /**
     *
     * @param searchContext - элемент относительно которого выполняется поиск
     */
    public CollectionPageObject(SearchContext searchContext){
        super(searchContext);
    }

    @Override
    public boolean isLoaded() {
        return true;
    }
}
