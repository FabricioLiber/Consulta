package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import dao.ConsultaDAO;
import fachada.Fachada;
import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class Main {
	
	public static void main (String[] args) {
		cadastro();
//		listar();
	}
	
	public static void cadastro () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		try {
//			Fachada.cadastrarUsuarios();
			Usuario u = Fachada.verificaUsuario("secretario", "secretario");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(2), "Cardiaco"));
			}
			else if (u instanceof Secretario) {
				ConsultaDAO consultaDAO = new ConsultaDAO();
				System.out.println(Fachada.confirmaConsulta(consultaDAO.readAll().get(0)));
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

	}
	
	public static void listar () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		try {
			System.out.println("Todas as Consultas: ");
			List<Consulta> consultas = Fachada.listaTodasAsConsultas();
			if (!consultas.isEmpty())
				System.out.println(consultas);
			else
				System.out.println("Nao existem consultas");
			System.out.println("Usuario do cpf 111.222.333-44: ");
			Usuario u = Fachada.PesquisarUsuarioPorCPF("111.222.333-44");
			System.out.println(u);			
			consultas = Fachada.listaConsultasPorUsuario(u);
			System.out.println("Consultas por usuario: ");
			if (!consultas.isEmpty())
				System.out.println(consultas);
			else
				System.out.println("Nao existem consultas");
			
			System.out.println("Consultas a realizar por Usuario: ");
			consultas = Fachada.listaConsultasARealizarPorUsuario();
			if (!consultas.isEmpty())
				System.out.println(consultas);
			else
				System.out.println("Nao existem consultas");
			
			System.out.println("Consultas realizadas por Usuario: ");
			consultas = Fachada.listaConsultasRealizadasPorUsuario();
			if (!consultas.isEmpty())
				System.out.println(consultas);
			else
				System.out.println("Nao existem consultas");
			
			Fachada.finalizar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
