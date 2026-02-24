package com.utem.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.utem.stepdefs", "com.utem.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html"
    }
)
public class TestRunner {
}
