package com.tradays.metaquotes.cucumber;

import com.tradays.metaquotes.core.exceptions.BussinessError;
import com.tradays.metaquotes.core.page.PageFactoryUtils;
import cucumber.api.Transformer;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
public class LanguageMatchingConverter extends Transformer<ILanguage> {

    @Override
    public ILanguage transform(String value) {
        try {
            LanguageEnum languageEnum = LanguageEnum.valueOf(value);
            return PageFactoryUtils.newInstance(languageEnum.aClass);
        }catch (IllegalArgumentException e){
            throw new BussinessError(String.format("Не найден маппинг для языка [%s]", value));
        }

    }
}
