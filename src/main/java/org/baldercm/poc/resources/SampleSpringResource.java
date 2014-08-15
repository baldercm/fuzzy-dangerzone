package org.baldercm.poc.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("api/sample/spring")
public class SampleSpringResource {

	private static final Logger logger = LoggerFactory.getLogger(SampleSpringResource.class);

	public SampleSpringResource() {
		System.out.println("SampleSpringResource");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		logger.debug("SampleResource.list()");

		List<Sample> samples = repository.findAll();
		samples.sort(Sample.DEFAULT_SORT);

		return Response.ok(samples).build();
	}

	@Autowired
	private SampleRepository repository;

}
