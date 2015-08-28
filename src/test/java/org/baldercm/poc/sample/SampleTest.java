package org.baldercm.poc.sample;

import org.baldercm.poc.config.PocConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PocConfig.class)
@WebAppConfiguration
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
