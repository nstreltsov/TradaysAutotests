package com.tradays.metaquotes.core.field;

import org.openqa.selenium.WebElement;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class RadioButton extends MobileElementFacade{

    public RadioButton(WebElement wrappedElement, String elementName) {
        super(wrappedElement, elementName);
    }

    @Override
    public void type(String value) {
        if (Boolean.valueOf(value) == isSelected()){
            return;
        }
        super.click();
    }

    @Override
    public boolean isSelected() {
        return Boolean.valueOf(super.getAttribute("checked"));
    }


}
