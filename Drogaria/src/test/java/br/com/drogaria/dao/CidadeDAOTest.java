package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Cidade;

public class CidadeDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		CidadeDAO daoC = new CidadeDAO();
		EstadoDAO daoE = new EstadoDAO();
		Cidade c = new Cidade();

		c.setNome("Curitiba");
		c.setEstado(daoE.buscar(1L));
		
		daoC.salvar(c);
	}
	
	@Test
	@Ignore
	public void listar() {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> list = dao.listar();
		
		for (Cidade c : list) {
			System.out.println(c.getCodigo()+" - "+c.getNome()+" - "+c.getEstado().getSigla());
		}
	}
	
	@Test
	public void listarPorEstado() {
		Long estado = 3L;
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> list = dao.listarPorUF(estado);
		
		for (Cidade c : list) {
			System.out.println(c.getCodigo()+" - "+c.getNome()+" - "+c.getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		CidadeDAO dao = new CidadeDAO();
		Long cod = 1L;
		Cidade c = dao.buscar(cod);
		
		if(c==null)
			System.out.println("Cidade não encontrada");
		else
			System.out.println(c.getCodigo()+" - "+c.getNome()+" - "+c.getEstado().getSigla());

	}
	
	@Test
	@Ignore
	public void excluir() {
		CidadeDAO dao = new CidadeDAO();
		Cidade c = dao.buscar(5L);

		if(c==null)
			System.out.println("Cidade não encontrada");
		else {
			System.out.println(c.getCodigo()+" - "+c.getNome()+" - "+c.getEstado().getSigla());
			dao.excluir(c);
		}
	}
	
	@Test
	@Ignore
	public void alterar() {
		CidadeDAO dao = new CidadeDAO();
		Cidade c = dao.buscar(1L);

		if(c==null)
			System.out.println("Cidade não encontrada");
		else {
			System.out.println(c.getCodigo()+" - "+c.getNome()+" - "+c.getEstado().getSigla());
			c.setNome("Colombo");
			dao.alterar(c);
		}
	}
}
