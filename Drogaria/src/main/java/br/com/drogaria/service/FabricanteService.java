package br.com.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@Path("fabricante")
public class FabricanteService {

	@GET
	public String listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar("descricao");
		
		Gson gson = new Gson();

		return gson.toJson(fabricantes);		
	}
	
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long c) {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f = dao.buscar(c);
		Gson gson = new Gson();
		return gson.toJson(f);
	}
	
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Fabricante f = gson.fromJson(json, Fabricante.class);
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f);
		return gson.toJson(f);
	}
	
	@PUT
	public String alterar(String json) {
		Gson gson = new Gson();
		Fabricante f = gson.fromJson(json, Fabricante.class);
		FabricanteDAO dao = new FabricanteDAO();
		dao.alterar(f);
		return gson.toJson(f);
	}
	
	@DELETE
	public String excluir(String json) {
		Gson gson = new Gson();
		Fabricante f = gson.fromJson(json, Fabricante.class);
		FabricanteDAO dao = new FabricanteDAO();
		f = dao.buscar(f.getCodigo());
		dao.excluir(f);
		return gson.toJson(f);
	}
}
