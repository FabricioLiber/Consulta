package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Medico extends Usuario {


	private String crm;
	private ArrayList<Especialidade> especialidades;
	
	public Medico(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			String crm) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.crm = crm;
		especialidades = new ArrayList<>();
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


	@Override
	public String toString() {
		
		return "Medico [crm=" + crm + ", especialidades=" + especialidades + ", getUser()=" + getUser()
				+ ", getPassword()=" + Arrays.toString(getPassword()) + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getDataNasc()=" + getDataNasc() + ", getEndereco()=" + getEndereco()
				+ ", getTelefones()=" + getTelefones() + ", getConsultas()=" + getConsultas() + "]";
	}
	
	
	
}
