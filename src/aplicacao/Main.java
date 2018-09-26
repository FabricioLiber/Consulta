package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import fachada.Fachada;
import modelo.Usuario;

public class Main {
	
	public static void main (String[] args) {
		// Teste metodos em Fachada

//		Fachada.inicializar();
//		Fachada.cadastrarUsuarios();
		try {
			Usuario u = Fachada.verificaUsuario("fabricio_liber2", "teste2");
			if (u != null)
				System.out.println(u);
			else
				System.out.println("nao funfou");
			Fachada.finalizar();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
