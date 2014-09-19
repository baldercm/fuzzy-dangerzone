package org.baldercm.poc.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.baldercm.poc.config.PocConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PocConfig.class)
public class SampleResourceTest {

	@Test
	public void shouldFindAllSamples() {
		Response response = sampleResource.find();

		assertEquals("Wrong response status", Status.OK.getStatusCode(), response.getStatus());
	}

	@Autowired
	private SampleResource sampleResource;

}
