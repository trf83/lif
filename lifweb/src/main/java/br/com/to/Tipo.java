package br.com.to;

import java.io.Serializable;

public class Tipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6484782463841867644L;
	private Integer id;
	private String nomeTipo;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

}
