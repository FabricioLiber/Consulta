package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("Medico")
public class Medico extends Usuario {

	private String crm;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Especialidade> especialidades = new ArrayList<>();;
	
	public Medico(String user, String password, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			String crm) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.crm = crm;
	}	

	public Medico() {
		super();
	}

	public String getCrm () {
		return this.crm;
	}
	
	public void add (Especialidade e) {
		especialidades.add(e);
	}

	public void remove (Especialidade e) {
		especialidades.remove(e);
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	@Override
	public String toString() {
		
		return "Medico [crm=" + crm + ", especialidades=" + especialidades.size() + ", getUser()=" + getUser()
				+ ", getPassword()=" + getPassword() + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco()
				+ ", getTelefones()=" + getTelefones() + ", getConsultas()=" + getConsultas() + "]";
	}
	
	
	
}
