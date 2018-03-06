package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	private void listar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			this.clientes = dao.listar("dataCadastro");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Clientes");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			PessoaDAO dao = new PessoaDAO();

			this.cliente = new Cliente();
			this.pessoas = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao criar novo Clientes");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO daoC = new ClienteDAO();
			daoC.merge(this.cliente);
			Messages.addGlobalInfo("Cliente Salvo!");
			novo();
			this.clientes = daoC.listar("dataCadastro");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Cliente!");
			e.printStackTrace();
		}

	}

	public void excluir(ActionEvent ae) {
		try {
			ClienteDAO dao = new ClienteDAO();
			this.cliente = (Cliente) ae.getComponent().getAttributes().get("clienteSelecionado");
			dao.excluir(this.cliente);
			Messages.addGlobalInfo("Cliente Excluído!");
			this.clientes = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Cliente!");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent ae) {
		try {
			this.cliente = (Cliente) ae.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDAO daoP = new PessoaDAO();
			this.pessoas = daoP.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Pessoa!");
			e.printStackTrace();
		}
	}
}