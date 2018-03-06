package br.com.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	Produto produto;
	List<Produto> produtos;
	List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void novo() {
		try {
			this.produto = new Produto();

			FabricanteDAO daoF = new FabricanteDAO();
			this.fabricantes = daoF.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao gerar novo Produto!");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao listar Produtos!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(produto.getCaminho() == null) {
				Messages.addGlobalError("Foto é obrigatória!");
				return;
			}
			ProdutoDAO dao = new ProdutoDAO();
			Produto p = dao.merge(produto);
			
			Path source = Paths.get(produto.getCaminho());
			Path target = Paths.get("H:/Programacao Web com Java/Uploads/"+p.getCodigo()+".png");
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Produto Salvo!");
			novo();
			this.produtos = dao.listar();
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Erro ao salvar Produto!");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			this.produto = (Produto) ae.getComponent().getAttributes().get("produtoSelecionado");
			dao.excluir(produto);
			
			Path path = Paths.get("H:/Programacao Web com Java/Uploads/"+produto.getCodigo()+".png");
			Files.deleteIfExists(path);
			
			Messages.addGlobalInfo("Produto excluído!");
			this.produtos = dao.listar();
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Erro ao excluir Produto!");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent ae) {
		try {
			this.produto = (Produto) ae.getComponent().getAttributes().get("produtoSelecionado");
			this.produto.setCaminho("H:/Programacao Web com Java/Uploads/"+produto.getCodigo()+".png");
			FabricanteDAO dao = new FabricanteDAO();
			this.fabricantes = dao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao alterar Produto!");
			e.printStackTrace();
		}
	}

	public void upload(FileUploadEvent e) {
		try {
			UploadedFile file = e.getFile();
			Path temp = Files.createTempFile(null, null);
			Files.copy(file.getInputstream(), temp, StandardCopyOption.REPLACE_EXISTING);
			this.produto.setCaminho(temp.toString());
			Messages.addGlobalInfo("Upload da Foto realizado!");
		} catch (IOException ex) {
			Messages.addGlobalInfo("Erro no Upload");
			ex.printStackTrace();
		}
	}
}
