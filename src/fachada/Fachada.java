package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import modelo.Usuario;

public class Fachada {
	
	
	public static byte[] criptografaSenha (String password) throws NoSuchAlgorithmException,
	UnsupportedEncodingException  {
		MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
		return algoritmo.digest(password.getBytes("UTF-8"));		
	}

	public static boolean verificaUsuario (String user, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Usuario u = new Usuario("teste1", criptografaSenha("teste1"));
		usuarios.add(u);
		u = new Usuario("teste2", criptografaSenha("teste2"));
		usuarios.add(u);
		
		
		byte[] senhaCriptografada = criptografaSenha(password);
		for (int i = 0; i < usuarios.size(); i++)
			if (usuarios.get(i).getUser().equals(user)) {
				for (int j = 0; j < senhaCriptografada.length; j++)
					if (senhaCriptografada[j] == usuarios.get(i).getPassword()[j])
						continue;
					else
						break;
				return true;
			}
		return false;
	}
	
}
