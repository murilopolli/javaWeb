package br.com.drogaria.dao;

import org.junit.Test;

import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		UsuarioDAO daoU = new UsuarioDAO();
		PessoaDAO daoP = new PessoaDAO();
		Pessoa p = daoP.buscar(1L);
		
		Usuario u = new Usuario();
		u.setAtivo(Boolean.TRUE);
		u.setPessoa(p);
		u.setSenha("12345");
		u.setTipo('A');
		
		daoU.salvar(u);
	}
}
