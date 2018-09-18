package modelo;

import java.time.LocalDate;

public class Paciente extends Pessoa {

	private Convenio convenio;
	
	public Paciente(String user, String password, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			Convenio convenio) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.convenio = convenio;
		
	}
	
	
	
	public Convenio getConvenio () {
		
		return this.convenio;
		
	}



	@Override
	public String toString() {
		return "Paciente [convenio=" + convenio + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco() + ", getTelefones()="
				+ getTelefones() + "]";
	}
	
	
	
	
}
