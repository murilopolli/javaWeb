package br.com.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	public void salvar() {
		FabricanteDAO daoF = new FabricanteDAO();
		Fabricante f = daoF.buscar(1L);
		
		Produto p = new Produto();
		p.setDescricao("Cataflan 50mg 20 comprimidos");
		p.setFabricante(f);
		p.setPreco(new BigDecimal("13.70"));
		p.setQuantidade(new Short("7"));
		
		ProdutoDAO daoP = new ProdutoDAO();
		daoP.salvar(p);
	}
}
