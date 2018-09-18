package modelo;

import java.time.LocalDate;

public class Medico extends Pessoa {
	private String crm;
	
	
	public Medico(String user, String password, String nome, String cpf, LocalDate dataNasc, Endereco endereco,
			String crm) {
		super(user, password, nome, cpf, dataNasc, endereco);
		this.crm = crm;
	}


	public String getCrm () {
		return this.crm;
	}

}
