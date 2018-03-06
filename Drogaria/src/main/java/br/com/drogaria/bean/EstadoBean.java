package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados; 

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		this.estado = new Estado();
	}

	public void salvar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			dao.merge(this.estado);

			Messages.addGlobalInfo("Estado Salvo!");
			this.estado = new Estado();
			this.estados = dao.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Estado!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			this.estados = dao.listar();
			
		} catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Estado!");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent ae) {
		try {
			EstadoDAO dao = new EstadoDAO();
		
			this.estado = (Estado) ae.getComponent().getAttributes().get("estadoSelecionado");
			dao.excluir(estado);
			this.estados = dao.listar();
			Messages.addGlobalInfo("Estado excluído!");
		} catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Estados!");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent ae) {
		this.estado = (Estado) ae.getComponent().getAttributes().get("estadoSelecionado");
	}
}