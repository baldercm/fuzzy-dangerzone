package org.baldercm.poc.resources;

import static org.junit.Assert.*;

import java.util.Collection;

import org.baldercm.poc.config.PocConfig;
import org.baldercm.poc.sample.Sample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PocConfig.class)
@WebAppConfiguration
public class SampleResourceTest {

	@Test
	public void shouldFindAllSamples() {
		ResponseEntity<Collection<Sample>> response = sampleResource.find();

		assertEquals("Wrong response status", HttpStatus.OK, response.getStatusCode());
	}

	@Autowired
	private SampleResource sampleResource;

}
