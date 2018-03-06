package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	Pessoa pessoa;
	Estado estado;
	List<Pessoa> pessoas;
	List<Cidade> cidades;
	List<Estado> estados;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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
			EstadoDAO daoE = new EstadoDAO();
			
			this.pessoa = new Pessoa();
			this.estado = new Estado();
			this.estados = daoE.listar("nome");
			this.cidades = new ArrayList<Cidade>();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao gerar nova Pessoa!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Pessoas!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.merge(pessoa);
			Messages.addGlobalInfo("Pessoa Salva!");
			novo();
			this.pessoas = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Pessoa!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			PessoaDAO dao = new PessoaDAO();
			this.pessoa = (Pessoa) ae.getComponent().getAttributes().get("pessoaSelecionada");
			dao.excluir(pessoa);
			Messages.addGlobalInfo("Pessoa Excluída!");
			this.pessoas = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Pessoa!");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent ae) {
		try {
			this.pessoa = (Pessoa) ae.getComponent().getAttributes().get("pessoaSelecionada");
			CidadeDAO daoC = new CidadeDAO();
			EstadoDAO daoE = new EstadoDAO();
			
			this.estado = this.pessoa.getCidade().getEstado();
			this.cidades = daoC.listarPorUF(this.estado.getCodigo());
			this.estados = daoE.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Pessoa!");
			e.printStackTrace();
		}
	}
	
	public void popularCidades() {
		try {
			if(this.estado != null) {
				CidadeDAO dao = new CidadeDAO();
				this.cidades = dao.listarPorUF(this.estado.getCodigo());
			} else {
				this.cidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao popular Cidades!");
			e.printStackTrace();
		}
	}
}
