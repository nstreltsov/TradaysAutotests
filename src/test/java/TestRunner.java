import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Основной Runner-класс для запуска feature-файлов
 *
 * @author Nikolay Streltsov on 03.11.2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.tradays.metaquotes.steps",
                "com.tradays.metaquotes.hook"},
        tags = {"@filter"},
        plugin = {"com.tradays.metaquotes.logging.AllureCucumberLogger"}
)
public class TestRunner {
}
