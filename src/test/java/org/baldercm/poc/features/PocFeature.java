package org.baldercm.poc.features;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.baldercm.poc.PocJerseyClient;
import org.baldercm.poc.ResponseHolder;
import org.baldercm.poc.config.PocConfig;
import org.baldercm.poc.sample.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = PocConfig.class)
@WebAppConfiguration
public class PocFeature {

	@Before("@deleteSamples")
	@After("@deleteSamples")
	public void deleteSamples() {
		client.deleteJson("/sample");
	}

	@Given("^the existing samples$")
	public void theExistingSamples(DataTable dataTable) {
		Consumer<Sample> doSaveSamples = (sample) -> {
			client.postJson("/sample", sample);
		};

		process(dataTable, doSaveSamples);
	}

	@When("^the user finds all samples$")
	public void theUserFindsAllSamples() {
		client.getJson("/sample");
	}

	@Then("^the user gets a list of all samples$")
	public void theUserGetsAListOfAllSamples() {
		Response response = responseHolder.getResponse();
		List<Sample> samples = response.readEntity(new GenericType<List<Sample>>() {});
		assertEquals("The samples are not the expected", 2, samples.size());
	}

	@Given("^no existing samples$")
	public void noExistingSamples() {
	}

	@When("^the user creates a sample$")
	public void theUserCreatesASample(DataTable dataTable) {
		Consumer<Sample> doPostSample = (sample) -> {
			client.postJson("/sample", sample);
		};

		process(dataTable, doPostSample);
	}

	@Then("^the sample is saved$")
	public void theSampleIsSaved() {
		Response response = client.getJson("/sample");
		int samplesCount = response.readEntity(new GenericType<Collection<Sample>>() {}).size();

		assertTrue("The sample count is not the expected", samplesCount == 1);
	}

	@Then("^the sample is not saved$")
	public void theSampleIsNotSaved() {
		Response response = client.getJson("/sample");
		int samplesCount = response.readEntity(new GenericType<Collection<Sample>>() {}).size();

		assertTrue("The sample count is not the expected", samplesCount == 0);
	}

	private void process(DataTable dataTable, Consumer<Sample> sampleConsumer) {
		dataTable
				.asMaps(String.class, String.class)
				.forEach((row) -> {
					String name = row.get("name");
					short age = Short.valueOf(row.get("age"));
					BigDecimal height = new BigDecimal(row.get("height"));

					Sample sample = new Sample(name, age, height);

					sampleConsumer.accept(sample);
				});
	}

	@Autowired
	private PocJerseyClient client;

	@Autowired
	private ResponseHolder responseHolder;

}
