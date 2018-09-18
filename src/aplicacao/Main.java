package aplicacao;

import java.time.LocalDate;

import modelo.Convenio;
import modelo.Endereco;
import modelo.Paciente;

public class Main {
	
	public static void main (String[] args) {
		String local = "Rua Desembargador Sindulfo,30,58301-180,Popular,Santa Rita,PB";
		String[] teste = local.split(",");
		Endereco endereco = new Endereco(teste[0], Integer.parseInt(teste[1]), teste[2], teste[3], teste[4], teste[5]);
		Convenio convenio = new Convenio("Unimed", 4.5);
		Paciente p = new Paciente("fabricio_liber", "12345", "Fabrício Liberato",
		"111.285.964-89", LocalDate.parse("1998-04-08"), endereco, convenio);
		System.out.println(p);
		p.add("(83) 98715-0546");
		System.out.println(p);
	}
}
