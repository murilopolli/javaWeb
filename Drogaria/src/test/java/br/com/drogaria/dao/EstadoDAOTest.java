package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Estado e = new Estado();
		e.setNome("Santa Catarina");
		e.setSigla("SC");

		EstadoDAO dao = new EstadoDAO();
		dao.salvar(e);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> list = dao.listar();

		for (Estado e : list) {
			System.out.println(e.getCodigo() + " - " + e.getSigla() + " - " + e.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		EstadoDAO dao = new EstadoDAO();
		Long c = 3L;
		Estado e = dao.buscar(c);

		if (e == null)
			System.out.println("Estado não encontrado");
		else
			System.out.println(e.getCodigo() + " - " + e.getSigla() + " - " + e.getNome());

	}

	@Test
	@Ignore
	public void excluir() {
		EstadoDAO dao = new EstadoDAO();
		Long c = 3L;
		Estado e = dao.buscar(c);

		if (e == null)
			System.out.println("Estado não encontrado");
		else {
			System.out.println(e.getCodigo() + " - " + e.getSigla() + " - " + e.getNome());
			dao.excluir(e);
		}

	}

	@Test
	@Ignore
	public void alterar() {
		EstadoDAO dao = new EstadoDAO();
		Long c = 1L;
		Estado e = dao.buscar(c);

		if (e == null)
			System.out.println("Estado não encontrato");
		else {
			e.setSigla("PR");
			e.setNome("Paraná");
			System.out.println(e.getCodigo() + " - " + e.getSigla() + " - " + e.getNome());
			dao.alterar(e);
		}
	}
	
	@Test
	public void merge() {
		Estado e = new Estado();
		e.setNome("Santa Catarina");
		e.setSigla("SC");
		
		EstadoDAO dao = new EstadoDAO();
		dao.merge(e);
	}
}
