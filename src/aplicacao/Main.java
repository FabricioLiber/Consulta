package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Scanner;

import fachada.Fachada;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Paciente;

public class Main {
	
	public static void main (String[] args) {
		String local = "Rua Desembargador Sindulfo,30,58301-180,Popular,Santa Rita,PB";
		String[] separadorEndereco = local.split(",");
		Endereco endereco = new Endereco(separadorEndereco[0], Integer.parseInt(separadorEndereco[1]),
				separadorEndereco[2], separadorEndereco[3], separadorEndereco[4], separadorEndereco[5]);
		Convenio convenio = new Convenio("Unimed", 4.5);
		Paciente p = null;
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			p = new Paciente("fabricio_liber", algoritmo.digest(("12345").getBytes("UTF-8")),
					"Fabrício Liberato", "111.285.964-89", LocalDate.parse("1998-04-08"), endereco, convenio);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Erro na criptografia");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Erro na transformação em bytes");
		}		
		System.out.println(p);
		p.add("(83) 98715-0546");

		System.out.println(p);
		
		Scanner scanner = new Scanner(System.in);
		String user = scanner.nextLine();
		String password = scanner.nextLine();
		scanner.close();
	
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte[] separadorEndereco1 = algoritmo.digest(password.getBytes("UTF-8"));
			byte[] testador = p.getPassword();
			if (user.equals(p.getUser()))
				for (int i = 0; i < separadorEndereco1.length; i++) {
					if(testador[i] != separadorEndereco1[i]) {
						System.out.print("senha incorreta");
						break;
					}
				}
			else
				System.out.print("usuario incorreto");
								
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Erro na instancia do algoritmo de criptografia");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro na transformação em bytes");
		}
		// Teste metodos em Fachada
		try {
			if (Fachada.verificaUsuario("teste1", "teste1"))
				System.out.println("funfou");
			else
				System.out.println("nao funfou");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
