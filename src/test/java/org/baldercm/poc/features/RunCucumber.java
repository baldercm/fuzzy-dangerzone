package org.baldercm.poc.features;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    monochrome = true,
    format = {"pretty", "html:target/cucumber-html-report"},
    features = "src/test/resources/org/baldercm/poc/features",
    snippets = SnippetType.CAMELCASE)
public class RunCucumber {
}
