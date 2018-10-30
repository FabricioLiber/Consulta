package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public static String getNomeUsuarioLogado() {
		return logado.getNome();
	}

	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
	
	

	public static Usuario realizarLogin (String user, String password) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		logado = usuarioDAO.verificaUsuario(user, geraHashBytes(password));
		return logado;
	}
	
	
	public static void realizarLogoff () throws Exception{
		if (logado == null)
			throw new Exception("Nao ha usuario logado!");
		logado = null;
	}
	
	
	public static void criarUsuario (Usuario u) {
		DAO.begin();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.create(u);
		DAO.commit();
	}
	
	public static void cadastrarEspecialidade (Especialidade e) {
		DAO.begin();
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		especialidadeDAO.create(e);
		DAO.commit();
	}
	
	public static void cadastrarConvenio (Convenio c) {
		DAO.begin();
		ConvenioDAO convenioDAO = new ConvenioDAO();
		convenioDAO.create(c);
		DAO.commit();
	}
	
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, String convenio) throws Exception {
		DAO.begin();
		Paciente usuario = new Paciente(user, geraHashBytes(password), nome, cpf, dataNasc, endereco, pesquisarConvenio(convenio));
		criarUsuario(usuario);
		DAO.commit();
		return usuario;
	}
	
	public static Consulta pesquisarPorNomeHorario (String horario, String primeiroNome) {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.pesquisaNomeHorario(horario, primeiroNome);
	}
	
	
	public static Usuario cadastrarUsuario(String user, String password, String nome, String cpf,
			LocalDate dataNasc, Endereco endereco, String crm, String especialidade) throws Exception {
		//TODO
		DAO.begin();
		Medico usuario = new Medico (user, geraHashBytes(password), nome, cpf, dataNasc, endereco, crm);
		Especialidade e = pesquisarEspecialidade(especialidade); 
		usuario.add(e);
		criarUsuario(usuario);
		System.out.println(e);
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
		ConsultaDAO consultaDAO = new ConsultaDAO();
		consultaDAO.create(consulta);
		paciente.add(consulta);
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		usuarioDAO.update(paciente);
		
		DAO.commit();
		return consulta;		
	}
	
	
	public static List<Medico> especialistasDisponiveisPorHorario (LocalDateTime horario, String especialidade) {
		// TODO
		MedicoDAO medicoDAO = new MedicoDAO();
		return medicoDAO.especialistasDisponiveis(horario, especialidade);
	}
	
	
	public static void adicionaEspecialidadeParaMedico (String cpfMedico, String especialidade) {
		DAO.begin();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Medico medico = (Medico) usuarioDAO.readByCpf(cpfMedico);
		medico.add(Fachada.pesquisarEspecialidade(especialidade));
		usuarioDAO.update(medico);
		DAO.commit();
		
	}
	
	
	public static Consulta confirmaConsulta (Consulta consulta, String cpfMedico) throws Exception {
		DAO.begin();
		Consulta c = pesquisarPorNomeHorario(consulta.getdataHorario().toString(), consulta.getPaciente().getNome().split(" ")[0]);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Secretario secretario = (Secretario) usuarioDAO.readByCpf(logado.getCpf());
		Medico medico = (Medico) usuarioDAO.readByCpf(cpfMedico);
		 
		c.setSecretario(secretario);
		c.setConfirmado(true);
		c.setMedico(medico);
		ConsultaDAO consultaDAO = new ConsultaDAO();

		consultaDAO.update(c);

		medico.add(c);
		usuarioDAO.update(medico);
		
		secretario.add(c);
		usuarioDAO.update(secretario);
		DAO.commit();
		return c;				
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
	
	public static Usuario pesquisarDadosUsuarioLogado () {
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		return usuarioDAO.readByCpf(logado.getCpf());
	}
	
	
	public static List<Especialidade> listaDeEspecialidades () {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
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
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasRealizadas(logado);
	}
	
	
	public static List<Consulta> listaConsultasARealizarPorUsuario () {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		return consultaDAO.consultasARealizar(logado);
	}
}
