package blackbox.com.ntnn.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {
        "pretty", "html:build/reports/cucumber/html",
                "json:build/reports/cucumber/cucumber.json",
                "junit:build/reports/cucumber/junit.xml",
                "usage:build/reports/cucumber/usage.jsonx"
        },
        publish = true
)
public class CucumberMain {
}
