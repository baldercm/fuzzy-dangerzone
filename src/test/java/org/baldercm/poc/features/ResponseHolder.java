package org.baldercm.poc.features;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

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
