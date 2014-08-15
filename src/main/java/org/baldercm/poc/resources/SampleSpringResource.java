package org.baldercm.poc.resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/api/sample")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SampleSpringResource {

	@GET
	public Response find() {
		logger.debug("SampleSpringResource.find()");

		Collection<Sample> samples = repository.findAll();

		return Response.ok(samples).build();
	}

	@GET
	@Path("/async")
	public void findAsync(@Suspended final AsyncResponse asyncResponse) {
		logger.debug("SampleSpringResource.findAsync()");

		new Thread(() -> {
			Collection<Sample> samples = repository.findAll();
			asyncResponse.resume(Response.ok(samples).build());
		}).start();
	}

	private static final Logger logger = LoggerFactory.getLogger(SampleSpringResource.class);

	@Autowired
	private SampleRepository repository;

}
