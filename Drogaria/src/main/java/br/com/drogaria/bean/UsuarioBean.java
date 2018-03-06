package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
			UsuarioDAO dao = new UsuarioDAO();
			this.usuarios = dao.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Usuarios");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			PessoaDAO dao = new PessoaDAO();

			this.usuario = new Usuario();
			this.pessoas = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao criar novo Usuario");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(this.usuario);
			Messages.addGlobalInfo("Usuario Salvo!");
			novo();
			this.usuarios = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar Usuario!");
			e.printStackTrace();
		}

	}

	public void excluir(ActionEvent ae) {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			this.usuario = (Usuario) ae.getComponent().getAttributes().get("usuarioSelecionado");
			dao.excluir(this.usuario);
			Messages.addGlobalInfo("Usuario Excluído!");
			this.usuarios = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao excluir Usuario!");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent ae) {
		try {
			this.usuario = (Usuario) ae.getComponent().getAttributes().get("usuarioSelecionado");
			PessoaDAO daoP = new PessoaDAO();
			this.pessoas = daoP.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Usuario!");
			e.printStackTrace();
		}
	}
}