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
import org.springframework.stereotype.Component;

@Component
public class PocJerseyClient {

	public PocJerseyClient() {
	}

	public PocJerseyClient(String host) {
		this();
		this.baseUri = host;
	}

	public Response getJson(String resourceUri) {
		return jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.get();
	}

	public Response getJson(String resourceUri, MultivaluedMap<String, ?> parameters) {
		WebTarget webTarget = jerseyClient()
				.target(baseUri)
				.path(resourceUri);
		webTarget = addQueryParameters(webTarget, parameters);

		return webTarget
				.request(MediaType.APPLICATION_JSON)
				.get();
	}

	public Response postJson(String resourceUri, Object dto) {
		return jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON));
	}

	public Response postJson(String resourceUri, Object dto, MultivaluedMap<String, ?> parameters) {
		WebTarget webTarget = jerseyClient()
				.target(baseUri)
				.path(resourceUri);

		webTarget = addQueryParameters(webTarget, parameters);

		return webTarget
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON));
	}

	public Response putJson(String resourceUri, Object data) {
		return jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(data, MediaType.APPLICATION_JSON));
	}

	public Response putJson(String resourceUri) {
		return jerseyClient()
				.target(baseUri)
				.path(resourceUri)
				.request(MediaType.APPLICATION_JSON)
				.delete();
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

}
