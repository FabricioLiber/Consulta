package modelo;

public class Endereco {
	private String logradouro;
	private int numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco(String logradouro, int numero, String cep, String bairro, String cidade, String estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
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

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
	
}
