package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import fachada.Fachada;
import modelo.Paciente;
import modelo.Usuario;

public class Teste {
	public static void main(String[] args) {
		try {
			Usuario usuario = new Usuario("1", Fachada.geraHashBytes("1"), "1", "1", LocalDate.now(), null);
			Usuario paciente = new Paciente("1", Fachada.geraHashBytes("1"), "1", "1", LocalDate.now(), null, null);
			System.out.println(paciente);
			System.out.println((Paciente) paciente);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
