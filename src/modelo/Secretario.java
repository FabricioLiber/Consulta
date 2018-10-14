package modelo;

import java.time.LocalDate;

public class Secretario extends Usuario {

	public Secretario(String user, byte[] password, String nome, String cpf, LocalDate dataNasc, 
			Endereco endereco) {
		super(user, password, nome, cpf, dataNasc, endereco);
	}	

}
