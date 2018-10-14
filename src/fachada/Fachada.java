package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.ConsultaDAO;
import dao.DAO;
import dao.EspecialidadeDAO;
import dao.UsuarioDAO;
import modelo.Consulta;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Especialidade;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class Fachada {
	
	private static Usuario logado;
	
	
	public static Usuario getLogado() {
		return logado;
	}

	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
	
	public static void cadastrarUsuarios ( ) throws Exception {
		DAO.begin();
		String local = "Rua Desembargador Sindulfo,30,58301-180,Popular,Santa Rita,PB";
		String[] separadorEndereco = local.split(",");
		Endereco endereco = new Endereco(separadorEndereco[0], Integer.parseInt(separadorEndereco[1]),
				separadorEndereco[2], separadorEndereco[3], separadorEndereco[4], separadorEndereco[5]);
		Convenio convenio = new Convenio("Unimed", 0.2);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(new Paciente ("paciente", geraHashBytes("paciente"), "Fabricio Liberato",
				"111.222.333-44", LocalDate.now(), endereco, convenio));
		Medico m = new Medico ("medico", geraHashBytes("medico"), "Kamila Freitas",
				"123.456.789-10", LocalDate.now(), endereco, "5467-0");
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade e = new Especialidade("Cardiaco", 120);
		System.out.println(e);
		e.add(m);
		especialidadeDAO.create(e);
		m.add(e);
		usuarioDAO.create(m);
		usuarioDAO.create(new Secretario ("secretario", geraHashBytes("secretario"), "Rafael Lins",
				"109.876.543-21", LocalDate.now(), endereco));
		DAO.commit();
	}
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, Convenio convenio) throws Exception {
		DAO.begin();
		Paciente usuario = new Paciente(user, geraHashBytes(password), nome, cpf, dataNasc, endereco, convenio);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(usuario);
		DAO.commit();
		return usuario;
	}
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, String crm, Especialidade especialidade) throws Exception {
		//TODO
		DAO.begin();
		Medico usuario = new Medico (user, geraHashBytes(password), nome, cpf, dataNasc, endereco, crm);
		usuario.add(especialidade);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(usuario);
		DAO.commit();
		return usuario;
	}
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco) throws Exception {
		//TODO
		DAO.begin();
		Secretario usuario = new Secretario (user, geraHashBytes(password), nome, cpf, dataNasc, endereco);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(usuario);
		DAO.commit();
		return usuario;
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
	
	public static Usuario verificaUsuario (String user, String password) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.realizaLogin(user, geraHashBytes(password));
		logado = usuario;
		return usuario;
	}
	
	// Operacoes com a Consulta Medica
	
	public static Consulta solicitaConsulta (LocalDateTime dataHorario, String especialidade) throws Exception {
		
		DAO.begin();
		Paciente paciente = (Paciente) pesquisarDadosUsuarioLogado();
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (dataHorario.toLocalDate().compareTo(amanha) < 0)
			throw new Exception("Consultas nao sao agendadas para o dia atual!");
		if (dataHorario.getHour() < 8 || dataHorario.getHour() > 16)
			throw new Exception("Atendimento apenas das 08:00 as 16:00 horas!");
		
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade e = especialidadeDAO.consultaEspecialidade(especialidade);
		Consulta consulta = new Consulta(dataHorario, paciente, false, e);
		
//		ConsultaDAO consultaDAO = new ConsultaDAO();
//		consultaDAO.create(consulta);		
		paciente.add(consulta);
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		usuarioDAO.update(paciente);
//		logado = paciente;
		
		DAO.commit();
		return consulta;		
	}
	
	
	public static List<Medico> especialistasDisponiveisPorHorario () {
		// TODO
//		List<Medico> medicos = 
		return null;
	}
	
	
	public static Consulta confirmaConsulta (Consulta consulta) throws Exception {
		DAO.begin();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Secretario secretario = (Secretario) usuarioDAO.readByCpf(logado.getCpf());
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (consulta.getdataHorario().toLocalDate().compareTo(amanha) > 0) {
			consulta.setSecretario(secretario);
			consulta.setConfirmado(true);
			List<Medico> medicos = listaMedicosPorEspecialidade(consulta.getEspecialidade());
			for (Medico m : medicos) {
				boolean ocupado = false;
				for (Consulta c : m.getConsultas())
					if (c.getdataHorario().compareTo(consulta.getdataHorario()) == 0) {
						ocupado = true;
						break;
					}
				if (!ocupado) {
					consulta.setMedico(m);
					consulta.getMedico().add(consulta);
					usuarioDAO.update(consulta.getMedico());
					consulta.getSecretario().add(consulta);
					usuarioDAO.update(consulta.getSecretario());
					ConsultaDAO consultaDAO = new ConsultaDAO();
					consultaDAO.update(consulta);
					DAO.commit();
					return consulta;
				}					
			}
			if (consulta.getMedico() == null)
				throw new Exception("Nao ha medicos disponiveis na especialidade solicitada!");
		} else
			throw new Exception("Consultas nao sao agendadas para o dia atual!");
			
		return null;
	}

	
	// Listagens
	
	public static List<Especialidade> listaDeEspecialidades () {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.readAll();
	}
	
	
	public static List<Consulta> listaConsultasSolicitadasPorPaciente () {
//		List<Consulta> consultas = null;
//		if (logado instanceof Paciente) {
//			consultas = new ArrayList<Consulta>();
//			for (Consulta c : logado.getConsultas())
//				if (!c.isConfirmado())
//					consultas.add(c);
//		}		
//		return consultas;
		Usuario usuario = pesquisarDadosUsuarioLogado();
		List<Consulta> consultas = null;
		if (logado instanceof Paciente) {
			consultas = new ArrayList<Consulta>();
			for (Consulta c : logado.getConsultas())
				if (!c.isConfirmado())
					consultas.add(c);
		}		
		return consultas;
	}
	
	
	public static List<Medico> listaMedicosPorEspecialidade (Especialidade e) {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultaMedicosPorEspecialidade(e);
	}
	
	
	
	public static List<Consulta> listaConsultasParaConfirmacao () {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasParaConfirmacao();
	}
	
	
	public static List<Consulta> listaConsultasPorUsuario (Usuario u) {
		return logado.getConsultas();
	}
	
	
	public static List<Consulta> listaConsultasRealizadasPorUsuario () {
		List<Consulta> consultasRealizadas = new ArrayList<>();
		if (!logado.getConsultas().isEmpty())
			for (Consulta c: logado.getConsultas())			
				if (c.getdataHorario().toLocalDate().compareTo(LocalDate.now()) < 0)
					consultasRealizadas.add(c);		
		return consultasRealizadas;
	}
	
	
	public static Usuario pesquisarDadosUsuarioLogado () {
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		return usuarioDAO.readByCpf(logado.getCpf());
	}
	
	
	public static List<Consulta> listaConsultasARealizarPorUsuario () {
		List<Consulta> consultasARealizar = new ArrayList<>();
		if (!logado.getConsultas().isEmpty())
			for (Consulta c: logado.getConsultas())			
				if (c.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0 && c.isConfirmado())
					consultasARealizar.add(c);		
		return consultasARealizar;
	}
	
	
	public static List<Consulta> listaTodasAsConsultas () {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.readAll();
	}
	
	
	public static List<Usuario> listaTodosUsuarios () {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.readAll();
	}
}
