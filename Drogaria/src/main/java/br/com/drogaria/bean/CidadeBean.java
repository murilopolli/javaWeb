package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		try {
			this.cidade = new Cidade();
			
			EstadoDAO daoE = new EstadoDAO();
			this.estados = daoE.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao gerar nova Cidade!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO daoC = new CidadeDAO();
			this.cidades = daoC.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Cidades!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO dao = new CidadeDAO();
			dao.merge(cidade);
			Messages.addGlobalInfo("Cidade salva!");
			novo();
			this.cidades = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Cidade!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			CidadeDAO dao = new CidadeDAO();
			this.cidade = (Cidade) ae.getComponent().getAttributes().get("cidadeSelecionada");
			dao.excluir(cidade);
			Messages.addGlobalInfo("Cidade excluída!");
			this.cidades = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Cidades!");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent ae) {
		try {		
			this.cidade = (Cidade) ae.getComponent().getAttributes().get("cidadeSelecionada");
			EstadoDAO daoE = new EstadoDAO();
			this.estados = daoE.listar("nome");
		} catch(RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Cidade!");
			e.printStackTrace();
		}
	}
}
