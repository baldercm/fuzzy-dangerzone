package org.baldercm.poc;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PocJerseyClient {

	public Response getJson(String resourceUri) {
		Response response = jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.get();

		responseHolder.setResponse(response);

		return response;
	}

	public Response getJson(String resourceUri, MultivaluedMap<String, ?> parameters) {
		WebTarget webTarget = jerseyClient()
				.target(baseUri)
				.path(resourceUri);
		webTarget = addQueryParameters(webTarget, parameters);

		Response response = webTarget
				.request(MediaType.APPLICATION_JSON)
				.get();

		responseHolder.setResponse(response);

		return response;
	}

	public Response postJson(String resourceUri, Object data) {
		Response response = jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(data, MediaType.APPLICATION_JSON));

		responseHolder.setResponse(response);

		return response;
	}

	public Response postJson(String resourceUri, Object data, MultivaluedMap<String, ?> parameters) {
		WebTarget webTarget = jerseyClient()
				.target(baseUri)
				.path(resourceUri);

		webTarget = addQueryParameters(webTarget, parameters);

		Response response = webTarget
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(data, MediaType.APPLICATION_JSON));

		responseHolder.setResponse(response);

		return response;
	}

	public Response putJson(String resourceUri, Object data) {
		Response response = jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(data, MediaType.APPLICATION_JSON));

		responseHolder.setResponse(response);

		return response;
	}

	public Response deleteJson(String resourceUri) {
		Response response = jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.delete();

		responseHolder.setResponse(response);

		return response;
	}

	private Client jerseyClient() {
		Client jerseyClient = ClientBuilder.newClient();
		jerseyClient.register(JacksonFeature.class);

		return jerseyClient;
	}

	private WebTarget addQueryParameters(WebTarget webTarget, MultivaluedMap<String, ?> queryParams) {
		if (queryParams != null) {
			for (String queryParamName : queryParams.keySet()) {
				List<?> queryParamValues = queryParams.get(queryParamName);
				for (Object queryParamValue : queryParamValues) {
					webTarget = webTarget.queryParam(queryParamName, queryParamValue);
				}
			}
		}

		return webTarget;
	}

	private String baseUri = "http://localhost:8080/poc/api";

	@Autowired
	private ResponseHolder responseHolder;

}
