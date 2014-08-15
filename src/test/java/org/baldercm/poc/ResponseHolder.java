package org.baldercm.poc;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class ResponseHolder {

	public void setResponse(Response response) {
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

	private Response response;

}
