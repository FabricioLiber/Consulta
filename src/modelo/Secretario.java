package modelo;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Secretario")
public class Secretario extends Usuario {

	
	public Secretario(String user, String password, String nome, String cpf, LocalDate dataNasc, 
			Endereco endereco) {
		super(user, password, nome, cpf, dataNasc, endereco);
	}
	
	public Secretario() {
		super();
	}
}
