package br.com.to;

import java.io.Serializable;

public class Telefone implements Serializable{


	private static final long serialVersionUID = 5607555345089211231L;
	private Integer ddd;
	private String numero;	


	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}	
}
