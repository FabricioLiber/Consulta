package modelo;

import java.time.LocalDate;
import java.util.Arrays;

public class Paciente extends Usuario {

	private Convenio convenio;
	
	
	public Paciente(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco, 
			Convenio convenio) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.convenio = convenio;
	}
	

	
	public Convenio getConvenio () {
		
		return this.convenio;
		
	}



	@Override
	public String toString() {
		return "Paciente [convenio=" + convenio + ", getUser()=" + getUser() + ", getPassword()="
				+ Arrays.toString(getPassword()) + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco() + ", getTelefones()="
				+ getTelefones() + ", getConsultas()=" + getConsultas() + "]";
	}



	
	
	
	
	
}
