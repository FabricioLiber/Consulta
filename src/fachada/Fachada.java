package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.ConsultaDAO;
import dao.ConvenioDAO;
import dao.DAO;
import dao.EspecialidadeDAO;
import dao.MedicoDAO;
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
	
	

	public static Usuario realizarLogin (String user, String password) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.verificaUsuario(user, geraHashBytes(password));
		logado = usuario;
		return usuario;
	}
	
	
	public static void realizarLogoff (){
		logado = null;
	}
	
	
	public static void criarUsuario (Usuario u) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(u);
	}	
	
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, String convenio) throws Exception {
		DAO.begin();
		Paciente usuario = new Paciente(user, geraHashBytes(password), nome, cpf, dataNasc, endereco, pesquisarConvenio(convenio));
		criarUsuario(usuario);
		DAO.commit();
		return usuario;
	}
	
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, String crm, String especialidade) throws Exception {
		//TODO
		DAO.begin();
		Medico usuario = new Medico (user, geraHashBytes(password), nome, cpf, dataNasc, endereco, crm);
		Especialidade e = pesquisarEspecialidade(especialidade); 
		usuario.add(e);
		criarUsuario(usuario);
		
		e.add(usuario);
		atualizarEspecialidade(e);
		
		DAO.commit();
		return usuario;
	}
	
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco) throws Exception {
		//TODO
		DAO.begin();
		Secretario usuario = new Secretario (user, geraHashBytes(password), nome, cpf, dataNasc, endereco);
		criarUsuario(usuario);
		DAO.commit();
		return usuario;
	}
	
	
	public static Especialidade atualizarEspecialidade (Especialidade e) {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.update(e);
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
	
	
	// Operacoes com a Consulta Medica
	
	public static Consulta solicitaConsulta (LocalDateTime dataHorario, String especialidade) throws Exception {
		
		DAO.begin();
		Paciente paciente = (Paciente) pesquisarDadosUsuarioLogado();
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (dataHorario.toLocalDate().compareTo(amanha) < 0)
			throw new Exception("Consultas nao sao agendadas para o dia atual!");
		if (dataHorario.getHour() < 8 || dataHorario.getHour() > 16)
			throw new Exception("Atendimento apenas das 08:00 as 16:00 horas!");
		
		Especialidade e = pesquisarEspecialidade(especialidade);
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
	
	
	public static List<Medico> especialistasDisponiveisPorHorario (LocalDateTime horario, String especialidade) {
		// TODO
		MedicoDAO medicoDAO = new MedicoDAO();
		List<Medico> medicos = medicoDAO.especialistasDisponiveis(horario, especialidade);
		return medicos;
	}
	
	
	public static Consulta confirmaConsulta (Consulta consulta) throws Exception {
		DAO.begin();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Secretario secretario = (Secretario) usuarioDAO.readByCpf(logado.getCpf());
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (consulta.getdataHorario().toLocalDate().compareTo(amanha) > 0) {
			consulta.setSecretario(secretario);
			consulta.setConfirmado(true);
			List<Medico> medicos = especialistasDisponiveisPorHorario(consulta.getdataHorario(), 
					consulta.getEspecialidade().getDescricao());
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
	
	public static Especialidade pesquisarEspecialidade (String especialidade) {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultaEspecialidade(especialidade);
	}
	
	public static Convenio pesquisarConvenio (String convenio) {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		return convenioDAO.consultaConvenio(convenio);
	}

	
	// Listagens
	
	public static List<Especialidade> listaDeEspecialidades () {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		System.out.println(especialidadeDAO.readAll());
		return especialidadeDAO.readAll();
	}
	
	
	public static List<Convenio> listaDeConvenios () {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		return convenioDAO.readAll();
	}
	
	
	public static List<Consulta> listaConsultas () {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.readAll();
	}
	
	
	public static List<Usuario> listaUsuarios () {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.readAll();
	}
	
	
	public static List<Consulta> listarConsultasSolicitadasPorPaciente () {		
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasSolicitadasPorPaciente(logado.getCpf());
	}
	
	
	public static List<Medico> listarMedicosPorEspecialidade (String especialidade) {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultaMedicosPorEspecialidade(pesquisarEspecialidade(especialidade));
	}
	
	
	public static List<Medico> listaMedicosPorEspecialidade (Especialidade especialidade) {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultaMedicosPorEspecialidade(especialidade);
	}
	
	
	
	public static List<Consulta> listaConsultasParaConfirmacao () {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasParaConfirmacao();
	}
	
	
	public static List<Consulta> listaConsultasPorUsuario (Usuario u) {
		return logado.getConsultas();
	}
	
	
	public static List<Consulta> listaConsultasRealizadasPorUsuario () {
//		List<Consulta> consultasRealizadas = new ArrayList<>();
//		if (!logado.getConsultas().isEmpty())
//			for (Consulta c: logado.getConsultas())			
//				if (c.getdataHorario().toLocalDate().compareTo(LocalDate.now()) < 0)
//					consultasRealizadas.add(c);		
//		return consultasRealizadas;
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasRealizadas(logado);
	}
	
	
	public static Usuario pesquisarDadosUsuarioLogado () {
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		System.out.println(logado.getCpf());
		return usuarioDAO.readByCpf(logado.getCpf());
	}
	
	
	public static List<Consulta> listaConsultasARealizarPorUsuario () {
//		List<Consulta> consultas = pesquisarDadosUsuarioLogado().getConsultas();
//		List<Consulta> consultasARealizar = new ArrayList<>();
//		if (!consultas.isEmpty())
//			for (Consulta c: logado.getConsultas())			
//				if (c.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0 && c.isConfirmado())
//					consultasARealizar.add(c);		
//		return consultasARealizar;
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasARealizar(logado);
	}
}
