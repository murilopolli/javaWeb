package br.com.drogaria.dao;

import java.util.Date;

import org.junit.Test;

import br.com.drogaria.domain.Cliente;

public class ClienteDAOTest {
	@Test
	public void salvar() {
		PessoaDAO daoP = new PessoaDAO();
		ClienteDAO daoC = new ClienteDAO();
		Cliente c = new Cliente();
		
		c.setDataCadastro(new Date());
		c.setLiberado(Boolean.TRUE);
		c.setPessoa(daoP.buscar(1L));
		
		daoC.salvar(c);
	}
}
