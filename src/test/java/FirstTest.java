import com.tradays.metaquotes.core.driver.WebDriverFacade;
import com.tradays.metaquotes.core.page.AbstractPageObject;
import com.tradays.metaquotes.core.page.IPageObject;
import com.tradays.metaquotes.cucumber.FieldTable;
import com.tradays.metaquotes.cucumber.FieldValueTable;
import com.tradays.metaquotes.page.EventPage;
import com.tradays.metaquotes.page.EventsPage;
import com.tradays.metaquotes.steps.scenario.CollectionScenarioSteps;
import com.tradays.metaquotes.steps.scenario.FieldScenarioSteps;
import com.tradays.metaquotes.steps.scenario.FilterScenarioSteps;
import com.tradays.metaquotes.steps.scenario.PageScenarioSteps;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FirstTest {

    FieldScenarioSteps fieldScenarioSteps = new FieldScenarioSteps();
    PageScenarioSteps pageScenarioSteps = new PageScenarioSteps();
    CollectionScenarioSteps collectionScenarioSteps = new CollectionScenarioSteps();

    @Before
    public void before(){
        WebDriverFacade.getDriver();
    }

    @Test
    public void firstTest(){
        EventsPage eventsPage = new EventsPage();
        eventsPage.getField("Фильтр").click();
        FilterScenarioSteps filterScenarioSteps = new FilterScenarioSteps();
        List<FieldValueTable> fieldValueTables = new ArrayList<>();
        fieldValueTables.add(new FieldValueTable("Наименование", "Medium"));
        fieldValueTables.add(new FieldValueTable("Наименование", "Switzerland"));
        filterScenarioSteps.stepSetFilter(fieldValueTables);
        List<IPageObject> items = AbstractPageObject.getCurrentPage().getCollection("Список событий");
        items.get(1).getField("Заголовок").click();

        pageScenarioSteps.stepLoadedPage(EventPage.class);
        String country = fieldScenarioSteps.getFieldValue("Страна");
        String importance = fieldScenarioSteps.getFieldValue("Важность");
        fieldScenarioSteps.clickField("История");

        List<FieldTable> fieldTables = new ArrayList<>();
        fieldTables.add(new FieldTable("Дата"));
        fieldTables.add(new FieldTable("Актуальное"));
        fieldTables.add(new FieldTable("Прогноз"));
        fieldTables.add(new FieldTable("Предыдущее"));
        collectionScenarioSteps.stepGetCollectionValuesByConditions("История индекса потребительского доверия", fieldTables, 10);
        System.err.println(items);

    }
}
