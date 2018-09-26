package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

import dao.DAO;
import dao.UsuarioDAO;
import modelo.Endereco;
import modelo.Usuario;

public class Fachada {
	
	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
	
	public static void cadastrarUsuarios ( ) {
		String local = "Rua Desembargador Sindulfo,30,58301-180,Popular,Santa Rita,PB";
		String[] separadorEndereco = local.split(",");
		Endereco endereco = new Endereco(separadorEndereco[0], Integer.parseInt(separadorEndereco[1]),
				separadorEndereco[2], separadorEndereco[3], separadorEndereco[4], separadorEndereco[5]);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.create(new Usuario ("fabricio_liber1", geraHashBytes("teste1"), "Fabrício Liberato",
					"111.222.333-44", LocalDate.now(), endereco));
			usuarioDAO.create(new Usuario ("fabricio_liber2", geraHashBytes("teste2"), "Fabrício Liberato",
					"111.222.333-44", LocalDate.now(), endereco));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] geraHashBytes (String password) throws NoSuchAlgorithmException,
	UnsupportedEncodingException  {
		MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
		return algoritmo.digest(password.getBytes("UTF-8"));		
	}

	public static String byteToHex (byte[] password) {
		StringBuilder sb = new StringBuilder();
	    for (byte b : password) {
	        sb.append(String.format("%02X", b));
	    }
		return sb.toString();		
	}
	
	public static Usuario verificaUsuario (String user, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		inicializar();
		List<Usuario> usuarios = usuarioDAO.readAll();
		byte[] senhaCriptografada = geraHashBytes(password);
		for (int i = 0; i < usuarios.size(); i++)
			if (usuarios.get(i).getUser().equals(user)) {
				for (int j = 0; j < senhaCriptografada.length; j++)
					if (senhaCriptografada[j] == usuarios.get(i).getPassword()[j])
						continue;
					else
						return null;
				return usuarios.get(i);
			}
		return null;
	}
	
}
