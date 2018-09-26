package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Usuario {

	private String user;
	private byte[] password;
	private String nome;
	private String cpf;
	private LocalDate dataNasc;
	private Endereco endereco;
	private ArrayList<String> telefones;
	
	
	public Usuario(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco) {
		super();
		this.user = user;
		this.password = password;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.telefones = new ArrayList<>();
	}


	public String getUser() {
		return user;
	}


	public byte[] getPassword() {
		return password;
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


	public void setUser(String user) {
		this.user = user;
	}
	
	
	public void add (String telefone) {
		this.telefones.add(telefone);
	}
	
	
	public void remove (String telefone) {
		this.telefones.remove(telefone);
	}


	@Override
	public String toString() {
		return "Usuario [user=" + user + ", password=" + Arrays.toString(password) + ", nome=" + nome + ", cpf=" + cpf
				+ ", dataNasc=" + dataNasc + ", endereco=" + endereco + ", telefones=" + telefones + "]";
	}
	
	
}
