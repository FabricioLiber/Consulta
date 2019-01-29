package modelo;

import java.time.LocalDate;

import javax.persistence.*;

import fachada.Fachada;

@Entity
@DiscriminatorValue("Paciente")
public class Paciente extends Usuario {

	@ManyToOne
	private Convenio convenio;
	
	
	public Paciente(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco, 
			Convenio convenio) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.convenio = convenio;
	}
	
	public Paciente() {
		super();
	}
	
	public Convenio getConvenio () {
		
		return this.convenio;
		
	}



	@Override
	public String toString() {
		return "Paciente [convenio=" + convenio + ", getUser()=" + getUser() + ", getPassword()="
				+ Fachada.byteToHex(getPassword()) + ", getId()=" + getId() +", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco() + ", getTelefones()="
				+ getTelefones() + ", getConsultas()=" + getConsultas().size() + "]";
	}



	
	
	
	
	
}
