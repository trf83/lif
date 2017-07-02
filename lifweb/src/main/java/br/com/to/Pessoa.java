package br.com.to;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.interfaces.ILIFParameter;

@Component("pessoa")
public class Pessoa implements Serializable, ILIFParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1455203335816525721L;
	private Integer id;
	private String nome;
	private Integer idade;
	private Cidade cidade;
	private Tipo tipoPessoa;
	private Collection<Telefone> telefones;
	private List<Veiculo> veiculos;
	
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
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Tipo getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(Tipo tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Collection<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(Collection<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}	
	public void setVeiculos(List<Veiculo> veiculo) {
		this.veiculos = veiculo;
	}
}
