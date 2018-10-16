package aplicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.ConsultaDAO;
import dao.ConvenioDAO;
import dao.EspecialidadeDAO;
import fachada.Fachada;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Especialidade;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class Main {
	
	public static void main (String[] args) {
		cadastro();
		listar();
//		teste();
	}
	
	public static void cadastro () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		System.out.println("criação de especialidades e convenios");
		try {

			// Criacao de especialidades
			EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
			Especialidade e = new Especialidade("Cancerologia", 170);
			especialidadeDAO.create(e);
			e = new Especialidade("Cardiologia", 120);
			especialidadeDAO.create(e);
			e = new Especialidade("dermatologia", 100);
			especialidadeDAO.create(e);
			e = new Especialidade("Ginecologia", 130);
			especialidadeDAO.create(e);
			e = new Especialidade("Neurologia", 160);
			especialidadeDAO.create(e);
			
			
			System.out.println("Lista de especialidades: \n");
			System.out.println(especialidadeDAO.readAll());
			
			// Criação de Convenios
			ConvenioDAO convenioDAO = new ConvenioDAO();
			Convenio c = new Convenio("Unimed", 0.3);
			convenioDAO.create(c);
			c = new Convenio("Hapvida", 0.4);
			convenioDAO.create(c);
			c = new Convenio("Amil", 0.5);
			convenioDAO.create(c);
			System.out.println("Lista de convênios: \n");
			System.out.println(convenioDAO.readAll());
			
			
			
		} catch (Exception e) {
			System.out.println("Erro na lista de especialidades ou convenios");
		}
		
		System.out.println("Cadastro dos usuários");
		try {
			Endereco endereco = new Endereco("Rua Desembargador Importante", 30, "58324-251", "Jaguaribe", "João Pessoa", "Paraíba");
			// Secretário
			System.out.println(Fachada.cadastrarUsuario("secretario", "secretario", "secretario", "222.222.222-22", LocalDate.of(2000, 2, 1), endereco));
			
			// Paciente
			System.out.println(Fachada.cadastrarUsuario("paciente", "paciente", "paciente", "111.111.111-11", LocalDate.of(1998, 4, 8), endereco, "Unimed"));
			
			// Médico
			System.out.println(Fachada.cadastrarUsuario("medico", "medico", "medico", "333.333.333-33", LocalDate.of(1990, 1, 1), endereco, "4567-4", "Neurologia"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro no cadastro de usuários");
		}
		
		System.out.println("Solicitação de Consulta");
		try {
			Usuario u = Fachada.realizarLogin("paciente", "paciente");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(10), "Cardiologia"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao solicitar consulta!");
		}
		System.out.println("Confirmação de Consulta");
		try {
			Usuario u = Fachada.realizarLogin("secretario", "secretario");
			if (u instanceof Secretario) {
				ConsultaDAO consultaDAO = new ConsultaDAO();
				System.out.println(Fachada.confirmaConsulta(consultaDAO.readAll().get(0), "333.333.333-33"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao confirmar consulta!");
		}
		Fachada.finalizar();
	}
	
	public static void listar () {
		// Teste metodos em Fachada

		Fachada.inicializar();
		try {
			System.out.println("Usuario");
			System.out.println(Fachada.realizarLogin("medico", "medico"));
			System.out.println("Consultas atendidas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			Fachada.realizarLogoff();
			
			System.out.println("Usuario");
			System.out.println(Fachada.realizarLogin("paciente", "paciente"));
			System.out.println("Consultas realizadas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			System.out.println("Consultas solicitadas");
			System.out.println(Fachada.listarConsultasSolicitadasPorPaciente());
			Fachada.realizarLogoff();
			
			System.out.println("Usuario");
			System.out.println(Fachada.realizarLogin("medico", "medico"));
			System.out.println("Consultas agendadas que ja foram realizadas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			System.out.println("Consultas para agendar");
			System.out.println(Fachada.listaConsultasParaConfirmacao());
			Fachada.realizarLogoff();
			
			Fachada.finalizar();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na listagem");
		}
	}
}
