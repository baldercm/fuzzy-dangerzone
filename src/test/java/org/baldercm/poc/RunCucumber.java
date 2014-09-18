package org.baldercm.poc;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		format = { "pretty", "html:target/cucumber-html-report" },
		features = "src/test/resources/org/baldercm/poc/features",
		snippets = SnippetType.CAMELCASE)
public class RunCucumber {
}
