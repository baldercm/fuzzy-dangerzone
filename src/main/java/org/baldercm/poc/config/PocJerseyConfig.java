package org.baldercm.poc.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class PocJerseyConfig extends ResourceConfig {

	public PocJerseyConfig() {
		packages("org.baldercm.poc");
		register(JacksonFeature.class);
	}
}
