package modelo;

import java.time.LocalDate;

public class Secretario extends Pessoa {

	public Secretario(String user, String password, String nome, String cpf, LocalDate dataNasc, Endereco endereco) {
		super(user, password, nome, cpf, dataNasc, endereco);
	}

}
