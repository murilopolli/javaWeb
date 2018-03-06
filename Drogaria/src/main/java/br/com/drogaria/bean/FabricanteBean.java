package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	Fabricante fabricante;
	List<Fabricante> fabricantes = new ArrayList<Fabricante>();

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void novo() {
		this.fabricante = new Fabricante();
	}

	public void salvar() {
		try {
			/* Via DAO */
			// FabricanteDAO dao = new FabricanteDAO();
			// dao.merge(this.fabricante);
			// novo();
			// this.fabricantes = dao.listar();

			/* Via serviço */
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/Drogaria/rest/fabricante");

			Gson gson = new Gson();

			String json = gson.toJson(this.fabricante);
			target.request().post(Entity.json(json));

			novo();

			json = target.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			this.fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante Salvo!");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Fabricante!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			/* Via DAO */
			// FabricanteDAO dao = new FabricanteDAO();
			// this.fabricantes = dao.listar();

			/* Via serviço */
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/Drogaria/rest/fabricante");
			String json = target.request().get(String.class);

			Gson gson = new Gson();
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

			this.fabricantes = Arrays.asList(vetor);
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Fabricantes!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			/* Via DAO */
			// FabricanteDAO dao = new FabricanteDAO();
			this.fabricante = (Fabricante) ae.getComponent().getAttributes().get("fabricanteSelecionado");
			// dao.excluir(fabricante);
			// this.fabricantes = dao.listar();

			/* Via serviço */
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/Drogaria/rest/fabricante");


			target.request().delete();

			novo();

			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			this.fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante excluído!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Fabricante!");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent ae) {
		try {
			this.fabricante = (Fabricante) ae.getComponent().getAttributes().get("fabricanteSelecionado");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Fabricante!");
			e.printStackTrace();
		}
	}
}
