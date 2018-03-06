package br.com.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drogaria")
public class DrogariaService {
	@GET
	public String exibir() {
		return "Sistema de Drogaria";
	}
}
