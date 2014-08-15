package org.baldercm.poc.features;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.baldercm.poc.PocJerseyClient;
import org.baldercm.poc.ResponseHolder;
import org.baldercm.poc.config.PocConfig;
import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = PocConfig.class)
public class PocFeature {

	@Given("^the existing Samples$")
	public void theExistingSamples(DataTable names) {
		createdSamples = new ArrayList<>();

		for (Map<String, String> row : names.asMaps(String.class, String.class)) {
			String name = row.get("name");
			short age = Short.valueOf(row.get("age"));
			BigDecimal height = new BigDecimal(row.get("age"));
			Sample sample = sampleRepository.save(new Sample(name, age, height));
			createdSamples.add(sample);
		}
	}

	@When("^the user find all samples$")
	public void theUserFindAllSamples() {
		Response response = client.getJson("/sample");

		responseHolder.setResponse(response);
	}

	@Then("^the user gets a list of all samples$")
	public void theUserGetsAListOfAllSamples() {
		Response response = responseHolder.getResponse();
		List<Sample> samples = response.readEntity(new GenericType<List<Sample>>() {
		});
		Assert.assertEquals("The samples are not the expected", createdSamples, samples);
	}

	@After("@usingSample")
	public void cleanup() {
		sampleRepository.deleteAll();
	}

	@Autowired
	private PocJerseyClient client;

	@Autowired
	private SampleRepository sampleRepository;

	@Autowired
	private ResponseHolder responseHolder;

	private List<Sample> createdSamples;

}
