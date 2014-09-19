package org.baldercm.poc.sample;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.baldercm.poc.config.PocConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PocConfig.class)
public class SampleTest {

	@Test
	public void shouldExecuteSomeImportantDomainOperation() {
		Sample sample = sampleRepository.save(new Sample("Joe", 22, BigDecimal.valueOf(1.80)));

		Sample modifiedSample = sample.someImportantDomainOperation();

		assertEquals("Wrong name", "Joe***", modifiedSample.getName());
	}

	@Autowired
	private SampleRepository sampleRepository;

}
