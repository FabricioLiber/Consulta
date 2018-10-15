package aplicacao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import dao.ConsultaDAO;
import fachada.Fachada;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class Main {
	
	public static void main (String[] args) {
//		cadastro();
		listar();
//		teste();
	}
	
	public static void cadastro () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		
//		try {
			// Criação de especialidades
			
//			EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
//			Especialidade e = new Especialidade("Cancerologia", 170);
//			especialidadeDAO.create(e);
//			e = new Especialidade("Cardiologia", 120);
//			especialidadeDAO.create(e);
//			e = new Especialidade("dermatologia", 100);
//			especialidadeDAO.create(e);
//			e = new Especialidade("Ginecologia", 130);
//			especialidadeDAO.create(e);
//			e = new Especialidade("Neurologia", 160);
//			especialidadeDAO.create(e);
//			
//			System.out.println(especialidadeDAO.readAll());
//			
//			// Criação de Convenios
//			ConvenioDAO convenioDAO = new ConvenioDAO();
//			Convenio c = new Convenio("Unimed", 0.3);
//			convenioDAO.create(c);
//			c = new Convenio("Hapvida", 0.4);
//			convenioDAO.create(c);
//			c = new Convenio("Amil", 0.5);
//			convenioDAO.create(c);
//			
//			System.out.println(convenioDAO.readAll());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			Usuario u = Fachada.realizarLogin("secretario", "secretario");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(18), "Cardiaco"));
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
		}finally {
			Fachada.finalizar();
		}		
	}
	
	public static void listar () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		try {
			Fachada.realizarLogin("fabricio", "fabricio");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
//			System.out.println(Fachada.listaMedicosPorEspecialidade("Cardiologia"));
//			System.out.println(Fachada.listaDeEspecialidades());
//			System.out.println("Todas as Consultas: ");
//			List<Consulta> consultas = Fachada.listaTodasAsConsultas();
//			if (!consultas.isEmpty())
//				System.out.println(consultas);
//			else
//				System.out.println("Nao existem consultas");
//			System.out.println("Usuario do cpf 111.222.333-44: ");
//			Usuario u = Fachada.pesquisarDadosUsuarioLogado();
//			System.out.println(u);			
//			consultas = Fachada.listaConsultasPorUsuario(u);
//			System.out.println("Consultas por usuario: ");
//			if (!consultas.isEmpty())
//				System.out.println(consultas);
//			else
//				System.out.println("Nao existem consultas");
//			
//			System.out.println("Consultas a realizar por Usuario: ");
//			consultas = Fachada.listaConsultasARealizarPorUsuario();
//			if (!consultas.isEmpty())
//				System.out.println(consultas);
//			else
//				System.out.println("Nao existem consultas");
//			
//			System.out.println("Consultas realizadas por Usuario: ");
//			consultas = Fachada.listaConsultasRealizadasPorUsuario();
//			if (!consultas.isEmpty())
//				System.out.println(consultas);
//			else
//				System.out.println("Nao existem consultas");
//			
			Fachada.finalizar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
