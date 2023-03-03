package TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD\\BDD_Exercise1.0\\src\\test\\java\\Features"},
        glue = {"StepDefinitions"}
)

public class TestRunner {
}
