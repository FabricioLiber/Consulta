package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import daoDB4O.IDInterface;

@Entity
public class Endereco implements IDInterface {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String logradouro;
	private int numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco(String logradouro, int numero, String cep, String bairro, String cidade, String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}


	public int getId () {
		return this.id;
	}
	
	
	public String getLogradouro() {
		return logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}


	public void setId (int id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
	
}
