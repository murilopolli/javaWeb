package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	List<Produto> produtos;
	List<ItemVenda> itens;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<ItemVenda> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.listar();
			
			this.itens = new ArrayList<ItemVenda>();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Produtos na tela de vendas!");
			e.printStackTrace();
		}
	}

	public void adicionar(ActionEvent ae) {
		Produto p = (Produto) ae.getComponent().getAttributes().get("produtoSelecionado");
		
		ItemVenda iv = new ItemVenda();
		iv.setProduto(p);
		iv.setQuantidade(new Short("1"));
		iv.setValorParcial(p.getPreco());
		
		itens.add(iv);
	}
}
