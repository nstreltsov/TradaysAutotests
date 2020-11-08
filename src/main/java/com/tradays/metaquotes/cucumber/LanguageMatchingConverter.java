package com.tradays.metaquotes.cucumber;

import com.tradays.metaquotes.core.exceptions.BussinessError;
import com.tradays.metaquotes.core.page.PageFactoryUtils;
import cucumber.api.Transformer;

/**
 * Конвертер для поиска реализации переключения на переданный в метод язык
 * например, при выполнении шага: установлен язык приложения "English"
 * будет найден класс описывающий механизм переключения с разных языков на English - EnglishLanguage.class
 *
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
