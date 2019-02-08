package aplicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import daoJPA.ConsultaDAO;
import daoJPA.UsuarioDAO;
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
//		cadastro();
		listar();
	}
	
	public static void cadastro () {
		// Teste metodos em Fachada
		
		ArrayList<Secretario> secretarios = new ArrayList<>();
		ArrayList<Paciente> pacientes = new ArrayList<>();
		ArrayList<Medico> medicos = new ArrayList<>();		
		
		Fachada.inicializar();
		System.out.println("cria��o de especialidades e convenios");
		try {
			// Criacao de especialidades
			Especialidade e = new Especialidade("Cancerologia", 170);
			Fachada.cadastrarEspecialidade(e);
			e = new Especialidade("Cardiologia", 120);
			Fachada.cadastrarEspecialidade(e);
			e = new Especialidade("Dermatologia", 100);
			Fachada.cadastrarEspecialidade(e);
			e = new Especialidade("Ginecologia", 130);
			Fachada.cadastrarEspecialidade(e);
			e = new Especialidade("Neurologia", 160);
			Fachada.cadastrarEspecialidade(e);
			
			
			System.out.println("Lista de especialidades: \n");
			System.out.println(Fachada.listaDeEspecialidades());
			
			// Cria��o de Convenios
			Convenio c = new Convenio("Unimed", 0.3);
			Fachada.cadastrarConvenio(c);
			c = new Convenio("Hapvida", 0.4);
			Fachada.cadastrarConvenio(c);
			c = new Convenio("Amil", 0.5);
			Fachada.cadastrarConvenio(c);
			System.out.println("Lista de conv�nios: \n");
			System.out.println(Fachada.listaDeConvenios());
			
			
			
		} catch (Exception e) {
			System.out.println("Erro na lista de especialidades ou convenios");
		}
		
		System.out.println("Cadastro dos usu�rios");
		try {
			// Endere�os
			Endereco endereco1 = new Endereco("Rua Desembargador Importante", 30, "58324-251", "Jaguaribe", "Jo�o Pessoa", "Para�ba");
			Endereco endereco2 = new Endereco("Rua Excelentissimo Juiz", 100, "57418-251", "Bairro do 11", "Recife", "Pernambuco");
			// Secret�rios
			secretarios.add((Secretario) Fachada.cadastrarUsuario("secretario1", "secretario1", "secretario1", "222.222.222-22", LocalDate.of(1992, 2, 1), endereco1));
			secretarios.add((Secretario) Fachada.cadastrarUsuario("secretario2", "secretario2", "secretario2", "333.333.333-33", LocalDate.of(2000, 2, 1), endereco2));


			// Pacientes
			pacientes.add((Paciente) Fachada.cadastrarUsuario("paciente1", "paciente1", "paciente1", "111.111.111-11", LocalDate.of(1998, 4, 8), endereco1, "Unimed"));
			pacientes.add((Paciente) Fachada.cadastrarUsuario("paciente2", "paciente2", "paciente2", "444.444.444-44", LocalDate.of(1996, 6, 8), endereco2, "Hapvida"));


			// M�dicos
			medicos.add((Medico) Fachada.cadastrarUsuario("medico1", "medico1", "medico1", "555.555.555-55", LocalDate.of(1970, 12, 11), endereco1, "4567-4", "Cardiologia"));
			medicos.add((Medico) Fachada.cadastrarUsuario("medico2", "medico2", "medico2", "666.666.666-66", LocalDate.of(1991, 10, 18), endereco2, "1237-8", "Neurologia"));
			medicos.add((Medico) Fachada.cadastrarUsuario("medico3", "medico3", "medico3", "777.777.777-77", LocalDate.of(1990, 1, 29), endereco2, "5643-5", "Dermatologia"));
			
			ArrayList<Usuario> usuarios = new ArrayList<>();
			usuarios.addAll(secretarios);
			usuarios.addAll(pacientes);
			usuarios.addAll(medicos);
			
			for (Usuario u : usuarios)
				System.out.println(u);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro no cadastro de usu�rios");
			e.printStackTrace();
		}
//		
		System.out.println("Solicita��o de Consulta");
		try {
			Usuario u = Fachada.realizarLogin("paciente1", "paciente1");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(10), "Cardiologia"));
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(11), "Neurologia"));
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(15), "Dermatologia"));	
			}
			Fachada.realizarLogoff();
			u = Fachada.realizarLogin("paciente2", "paciente2");
			if (u instanceof Paciente) {
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(20), "Cardiologia"));
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(7), "Cancerologia"));
				System.out.println(Fachada.solicitaConsulta(LocalDateTime.now().plusDays(5), "Ginecologia"));
			}
			Fachada.realizarLogoff();
			
			// Consultas realizadas
			ConsultaDAO consultaDAO = new ConsultaDAO();
			Consulta c = new Consulta(LocalDateTime.of(2018, 7, 8, 10, 0), pacientes.get(0), true, medicos.get(0).getEspecialidades().get(0));
			c.setMedico(medicos.get(0));
			c.setSecretario(secretarios.get(0));
			c.setPaciente(pacientes.get(0));
			consultaDAO.update(c);

			medicos.get(0).add(c);
			secretarios.get(0).add(c);
			pacientes.get(0).add(c);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.update(medicos.get(0));
			usuarioDAO.update(secretarios.get(0));
			usuarioDAO.update(pacientes.get(0));

			c = new Consulta(LocalDateTime.of(2018, 10, 2, 12, 0), pacientes.get(1), true, medicos.get(1).getEspecialidades().get(0));
			c.setMedico(medicos.get(1));
			c.setSecretario(secretarios.get(1));
			c.setPaciente(pacientes.get(1));
			consultaDAO.update(c);

			medicos.get(1).add(c);
			usuarioDAO.update(medicos.get(1));
			secretarios.get(1).add(c);
			usuarioDAO.update(secretarios.get(1));
			pacientes.get(1).add(c);
			usuarioDAO.update(pacientes.get(1));
			
			c = new Consulta(LocalDateTime.of(2018, 7, 8, 13, 0), pacientes.get(0), true, medicos.get(2).getEspecialidades().get(0));
			c.setMedico(medicos.get(2));
			c.setSecretario(secretarios.get(1));
			c.setPaciente(pacientes.get(0));
			consultaDAO.update(c);

			pacientes.get(0).add(c);
			usuarioDAO.update(pacientes.get(0));
			
			medicos.get(2).add(c);
			usuarioDAO.update(medicos.get(2));
			
			secretarios.get(1).add(c);
			usuarioDAO.update(secretarios.get(1));
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao solicitar consulta!");
			e.printStackTrace();
		}
		System.out.println("Confirmacao de Consulta");
		try {
			Usuario u = Fachada.realizarLogin("secretario1", "secretario1");
			if (u instanceof Secretario) {
				System.out.println(Fachada.confirmaConsulta(Fachada.listaConsultas().get(0), "555.555.555-55"));
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
			System.out.println("Listar todas as consultas: ");
			System.out.println(Fachada.listaConsultas());
			System.out.println("Consultas dos Medicos\n");
			System.out.println(Fachada.realizarLogin("medico1", "medico1"));
			System.out.println("Consultas atendidas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			Fachada.realizarLogoff();
			
			System.out.println("\n");
			System.out.println(Fachada.realizarLogin("medico2", "medico2"));
			System.out.println("Consultas atendidas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			Fachada.realizarLogoff();
			
			System.out.println("\n");
			System.out.println(Fachada.realizarLogin("medico3", "medico3"));
			System.out.println("Consultas atendidas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			Fachada.realizarLogoff();
			
			System.out.println("\nConsultas dos Pacientes\n");
			System.out.println(Fachada.realizarLogin("paciente1", "paciente1"));
			System.out.println("Consultas realizadas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			System.out.println("Consultas solicitadas");
			System.out.println(Fachada.listarConsultasSolicitadasPorPaciente());
			Fachada.realizarLogoff();
			
			System.out.println("\n");
			System.out.println(Fachada.realizarLogin("paciente2", "paciente2"));
			System.out.println("Consultas realizadas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			System.out.println("Consultas solicitadas");
			System.out.println(Fachada.listarConsultasSolicitadasPorPaciente());
			Fachada.realizarLogoff();
			
			System.out.println("\nConsultas dos Secretarios\n");
			System.out.println(Fachada.realizarLogin("secretario1", "secretario1"));
			System.out.println("Consultas agendadas que ja foram realizadas");
			System.out.println(Fachada.listaConsultasRealizadasPorUsuario());
			System.out.println("Consultas agendadas");
			System.out.println(Fachada.listaConsultasARealizarPorUsuario());
			System.out.println("Consultas para agendar");
			System.out.println(Fachada.listaConsultasParaConfirmacao());
			Fachada.realizarLogoff();
			
			System.out.println("\n");
			System.out.println(Fachada.realizarLogin("secretario2", "secretario2"));
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
			e.printStackTrace();
		}
	}
}
