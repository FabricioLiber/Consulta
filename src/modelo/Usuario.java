package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import daoDB4O.IDInterface;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",
						discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Usuario")
public class Usuario implements IDInterface {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String user;
	private byte[] password;
	private String nome;
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private LocalDate dataNasc;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Endereco endereco;
	
	@ElementCollection
	private List<String> telefones;
	
	@OneToMany
	private List<Consulta> consultas;
	
	
	public Usuario(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco) {
		this.user = user;
		this.password = password;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.telefones = new ArrayList<>();
		this.consultas = new ArrayList<>();
	}


	public int getId () {
		return this.id;
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


	public List<String> getTelefones() {
		return telefones;
	}


	public void setId (int id) {
		this.id = id;
	}
	

	public void setUser(String user) {
		this.user = user;
	}
	
	
	public List<Consulta> getConsultas() {
		return consultas;
	}


	public void add (String telefone) {
		this.telefones.add(telefone);
	}
	
	
	public void remove (String telefone) {
		this.telefones.remove(telefone);
	}
	
	public void add (Consulta consulta) {
		this.consultas.add(consulta);
	}
	
	
	public void remove (Consulta consulta) {
		this.consultas.remove(consulta);
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + "user=" + user + ", password=" + Arrays.toString(password) + ", nome=" + nome + ", cpf=" + cpf
				+ ", dataNasc=" + dataNasc + ", endereco=" + endereco + ", telefones=" + telefones + ", consultas="
				+ consultas + "]";
	}
	
	
}
