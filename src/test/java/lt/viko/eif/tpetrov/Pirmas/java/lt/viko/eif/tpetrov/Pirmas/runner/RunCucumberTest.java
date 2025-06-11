package lt.viko.eif.tpetrov.Pirmas.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "lt.viko.eif.tpetrov.Pirmas.definitions"
)
public class RunCucumberTest {
}
