package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import dao.ConsultaDAO;
import dao.PacienteDAO;
import fachada.Fachada;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class Main {
	
	public static void main (String[] args) {
		// Teste metodos em Fachada

		Fachada.inicializar();
		try {
//			Fachada.cadastrarUsuarios();
			Usuario u = Fachada.verificaUsuario("secretario", "secretario");
			if (u instanceof Paciente) {
				Paciente p = (Paciente) u;
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(2).plusHours(9), "Cardiaco", p));
			}
			else if (u instanceof Secretario) {
				System.out.println("É secretario");
				Secretario s = (Secretario) u;
				ConsultaDAO consultaDAO = new ConsultaDAO();
				System.out.println(Fachada.confirmaConsulta(consultaDAO.readAll().get(0), s));
			}
			else if (u instanceof Medico) {
				System.out.println("Medico");				
			}
			Fachada.finalizar();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		LocalDateTime amanha = LocalDateTime.now().plusHours(1);
//		LocalDateTime hoje = LocalDateTime.now();
//		System.out.println(hoje);
//		System.out.println(amanha);
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 2; j++)
//				break;
//			System.out.println("Entrou mesmo assim");
//		}
//		
//		if (hoje.compareTo(amanha) > 0)
//			System.out.println("Hoje é maior q amanha");
//		else if (hoje.compareTo(amanha) == 0)
//			System.out.println("Hoje é igual a amanha");
//		else
//			System.out.println("Hoje é menor que amanha");
	}
}
