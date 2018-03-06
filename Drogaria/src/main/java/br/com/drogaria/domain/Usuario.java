package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = false)
	private Boolean ativo;

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Transient
	public String getTipoExtenso() {
		String tipoExtenso = null;
		switch (this.tipo) {
		case 'A':
			tipoExtenso = "Administrador";
			break;
		case 'B':
			tipoExtenso = "Balconista";
			break;
		case 'G':
			tipoExtenso = "Gerente	";
			break;
		default:
			break;
		}
		
		return tipoExtenso;
	}
	
	@Transient
	public String getTextoAtivo() {
		return (this.ativo?"Sim":"Não");
	}

}
