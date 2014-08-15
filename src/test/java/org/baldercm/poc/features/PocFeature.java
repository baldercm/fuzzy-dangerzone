package org.baldercm.poc.features;

import static org.junit.Assert.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PocFeature {

	@Given("^an existing feature file$")
	public void an_existing_feature_file() throws Throwable {
		assertTrue("Given ok", true);
	}

	@When("^I ran my integration-tests$")
	public void i_ran_my_integration_tests() throws Throwable {
		throw new PendingException();
	}

	@Then("^integration-tests executes successfully$")
	public void integration_tests_executes_successfully() throws Throwable {
		throw new PendingException();
	}

}
