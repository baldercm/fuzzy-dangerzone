package org.baldercm.poc.config;

import javax.annotation.PostConstruct;

import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoInit {

	@PostConstruct
	public void init() {
		repository.deleteAll();

		repository.save(new Sample("Balder"));
		repository.save(new Sample("Alba"));
		repository.save(new Sample("Lara"));
	}

	@Autowired
	private SampleRepository repository;

}
