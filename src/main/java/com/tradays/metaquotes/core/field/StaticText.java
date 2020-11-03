package com.tradays.metaquotes.core.field;

import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class StaticText extends MobileElementFacade{

    public StaticText(WebElement wrappedElement, String elementName) {
        super(wrappedElement, elementName);
    }

    @Override
    public void type(String value){
        throw new UnsupportedOperationException(String.format("Объект [%s] не поддеривает операцию присваивания значения", super.elementName));
    }
}
