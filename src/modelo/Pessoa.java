package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pessoa extends Usuario {
	
	private String nome;
	private String cpf;
	private LocalDate dataNasc;
	private Endereco endereco;
	private ArrayList<String> telefones;
		
	
	public Pessoa(String user, String password, String nome, String cpf, LocalDate dataNasc, Endereco endereco) {
		super(user, password);
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.telefones = new ArrayList<>();
	}



	public String getNome() {
		return nome;
	}



	public String getCpf() {
		return cpf;
	}



	public LocalDate getDataNasc() {
		return dataNasc;
	}



	public Endereco getEndereco() {
		return endereco;
	}



	public ArrayList<String> getTelefones() {
		return telefones;
	}



	public void add (String telefone) {
		telefones.add(telefone);
	}
	
	
	
	public void remove (String telefone) {
		telefones.remove(telefone);
	}
	
}
