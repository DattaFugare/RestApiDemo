package Cucumber.Option;

import io.cucumber.junit.CucumberOptions;
import  org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"},glue = {"StepDefinitions"})
public class TestRunner {
}
