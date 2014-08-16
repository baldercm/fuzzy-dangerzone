package org.baldercm.poc.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/api/sample")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SampleSpringResource {

	@GET
	public Response find() {
		Collection<Sample> samples = repository.findAll();
		return Response.ok(samples).build();
	}

	@GET
	@Path("/async")
	public void findAsync(@Suspended final AsyncResponse asyncResponse) {
		new Thread(() -> {
			Collection<Sample> samples = repository.findAll();
			asyncResponse.resume(Response.ok(samples).build());
		}).start();
	}

	@POST
	public Response create(@Valid @NotNull Sample sample) {
		sample = repository.save(sample);
		return Response.status(Status.CREATED).entity(sample).build();
	}

	@POST
	@Path("/troll")
	public Response createNoValidation(@NotNull Sample sample) {
		Set<ConstraintViolation<Sample>> validationErrors = validator.validate(sample);
		Collection<String> validationErrorMessages = new ArrayList<>();
		validationErrors.forEach((validationError) ->
				validationErrorMessages.add(validationError.getMessage())
				);

		if (!validationErrorMessages.isEmpty())
			return Response.status(Status.BAD_REQUEST).entity(validationErrorMessages).build();

		sample = repository.save(sample);
		return Response.status(Status.CREATED).entity(sample).build();
	}

	@Autowired
	private SampleRepository repository;

	@Autowired
	private javax.validation.Validator validator;

}
