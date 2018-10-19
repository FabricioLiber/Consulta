package aplicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.ConsultaDAO;
import dao.ConvenioDAO;
import dao.EspecialidadeDAO;
import dao.UsuarioDAO;
import fachada.Fachada;
import modelo.Consulta;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Especialidade;
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
		
		ArrayList<Secretario> secretarios = new ArrayList<>();
		ArrayList<Paciente> pacientes = new ArrayList<>();
		ArrayList<Medico> medicos = new ArrayList<>();
		
		Fachada.inicializar();
		System.out.println("criação de especialidades e convenios");
		try {
			
			/**
		    Criar adiciona Especialidade a medico
		 */


		// Solicita Consulta

		Usuario u = Fachada.realizarLogin("paciente1", "paciente1");
		System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(3), "Neurologia"));
		System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(15), "Dermatologia"));

		u = Fachada.realizarLogin("paciente2", "paciente2");
		System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(20), "Cardiologia"));
		System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(7), "Cancerologia"));
		System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(5), "Ginecologia"));


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
			// Endereços
			Endereco endereco1 = new Endereco("Rua Desembargador Importante", 30, "58324-251", "Jaguaribe", "João Pessoa", "Paraíba");
			Endereco endereco2 = new Endereco("Rua Excelentissimo Juiz", 100, "57418-251", "Bairro do 11", "Recife", "Pernambuco");
			// Secretários
			secretarios = new ArrayList<>();
			System.out.println(Fachada.cadastrarUsuario("secretario1", "secretario1", "secretario1", "222.222.222-22", LocalDate.of(1992, 2, 1), endereco1));
			System.out.println(Fachada.cadastrarUsuario("secretario2", "secretario2", "secretario2", "333.333.333-33", LocalDate.of(2000, 2, 1), endereco2));


			// Pacientes
			pacientes = new ArrayList<>();
			System.out.println(Fachada.cadastrarUsuario("paciente1", "paciente1", "paciente1", "111.111.111-11", LocalDate.of(1998, 4, 8), endereco1, "Unimed"));
			System.out.println(Fachada.cadastrarUsuario("paciente2", "paciente2", "paciente2", "444.444.444-44", LocalDate.of(1996, 6, 8), endereco2, "Hapvida"));


			// Médicos
			medicos = new ArrayList<>();
			System.out.println(Fachada.cadastrarUsuario("medico1", "medico1", "medico1", "555.555.555-55", LocalDate.of(1970, 12, 11), endereco1, "4567-4", "Cardiologia"));
			System.out.println(Fachada.cadastrarUsuario("medico2", "medico2", "medico2", "666.666.666-66", LocalDate.of(1991, 10, 18), endereco2, "4567-4", "Neurologia"));
			System.out.println(Fachada.cadastrarUsuario("medico3", "medico3", "medico3", "777.777.777-77", LocalDate.of(1990, 1, 29), endereco2, "4567-4", "Dermatologia"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro no cadastro de usuários");
		}
//		
		System.out.println("Solicitação de Consulta");
		try {
			Usuario u = Fachada.realizarLogin("paciente", "paciente");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(10).plusHours(4), "Cardiologia"));
			}
			// Consultas realizadas
			ConsultaDAO consultaDAO = new ConsultaDAO();
			Consulta c = new Consulta(LocalDateTime.of(2018, 7, 8, 10, 0), pacientes.get(0), true, medicos.get(0).getEspecialidades().get(0));
			c.setMedico(medicos.get(0));
			c.setSecretario(secretarios.get(0));
			consultaDAO.update(c);

			medicos.get(0).add(c);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.update(medicos.get(0));

			secretarios.get(0).add(c);
			usuarioDAO.update(secretarios.get(0));

			c = new Consulta(LocalDateTime.of(2018, 10, 2, 12, 0), pacientes.get(1), true, medicos.get(1).getEspecialidades().get(0));
			c.setMedico(medicos.get(1));
			c.setSecretario(secretarios.get(1));
			consultaDAO.update(c);

			medicos.get(1).add(c);
			usuarioDAO.update(medicos.get(1));

			secretarios.get(1).add(c);
			usuarioDAO.update(secretarios.get(1));

			c = new Consulta(LocalDateTime.of(2018, 7, 8, 13, 0), pacientes.get(0), true, medicos.get(2).getEspecialidades().get(0));
			c.setMedico(medicos.get(2));
			c.setSecretario(secretarios.get(1));
			consultaDAO.update(c);

			medicos.get(2).add(c);
			usuarioDAO.update(medicos.get(2));

			secretarios.get(1).add(c);
			usuarioDAO.update(secretarios.get(1));
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao solicitar consulta!");
			e.printStackTrace();
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
			e.printStackTrace();
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
