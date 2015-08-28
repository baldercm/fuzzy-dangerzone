package org.baldercm.poc.features;

import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = PocCucumberConfig.class)
public class ThenResponseIs {

    @Then("^response is \"([^\"]*)\"$")
    public void responseIs(String status) {
        int expectedStatusCode = Response.Status.valueOf(status).getStatusCode();
        int statusCode = responseHolder.getResponse().getStatus();
        assertEquals("The response must be " + status, expectedStatusCode, statusCode);
    }

    @Autowired
    private ResponseHolder responseHolder;

}
