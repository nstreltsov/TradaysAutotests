import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Nikolay Streltsov on 03.11.2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.tradays.metaquotes.steps",
                "com.tradays.metaquotes.hook"},
        tags = {"@development"}

)
public class TestRunner {
}
