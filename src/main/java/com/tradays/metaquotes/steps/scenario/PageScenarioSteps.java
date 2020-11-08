package com.tradays.metaquotes.steps.scenario;

import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import io.qameta.allure.Step;
import org.junit.Assert;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class PageScenarioSteps {

    @Step("страница \"{page}\" загружена")
    public IPageObject stepLoadedPage(Class<? extends IPageObject> page){
        IPageObject pageObject = setPage(page);
        pageShouldBeLoaded(pageObject);
        return pageObject;
    }

    private IPageObject setPage(Class<? extends IPageObject> pageClass) {
        return AbstractPageObject.setCurrentPage(pageClass);
    }

    private void pageShouldBeLoaded(IPageObject page) {
        Assert.assertTrue("Страница \"" + page.getClass().getCanonicalName() + "\" не загружена", page.isLoaded());
    }
}
