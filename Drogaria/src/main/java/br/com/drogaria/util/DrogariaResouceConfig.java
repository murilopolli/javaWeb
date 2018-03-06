package br.com.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class DrogariaResouceConfig extends ResourceConfig {
	public DrogariaResouceConfig() {
		packages("br.com.drogaria.service");
	}
}
