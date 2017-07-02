package br.com.to;

import java.io.Serializable;

public class Veiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7332529977082301935L;
	
	private Integer id;
	private String nome;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
