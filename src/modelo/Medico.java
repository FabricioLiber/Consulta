package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import fachada.Fachada;

@Entity
@DiscriminatorValue("Medico")
public class Medico extends Usuario {

	private String crm;
	
	@ManyToMany
	private List<Especialidade> especialidades;
	
	public Medico(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			String crm) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.crm = crm;
		especialidades = new ArrayList<>();
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
		
		return "Medico [crm=" + crm + ", especialidades=" + especialidades + ", getUser()=" + getUser()
				+ ", getPassword()=" + Fachada.byteToHex(getPassword()) + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco()
				+ ", getTelefones()=" + getTelefones() + ", getConsultas()=" + getConsultas().size() + "]";
	}
	
	
	
}
