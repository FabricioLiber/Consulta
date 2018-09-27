package fachada;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.ConsultaDAO;
import dao.DAO;
import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
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
		usuarioDAO.create(new Paciente ("paciente", geraHashBytes("paciente"), "Fabrício Liberato",
				"111.222.333-44", LocalDate.now(), endereco, convenio));
		Medico m = new Medico ("medico", geraHashBytes("medico"), "Fabrício Liberato",
				"111.222.333-44", LocalDate.now(), endereco, "5467-0");
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade e = new Especialidade("Cardiaco", 120);
		e.add(m);
		especialidadeDAO.create(e);
		m.add(e);
		usuarioDAO.create(m);
		usuarioDAO.create(new Secretario ("secretario", geraHashBytes("secretario"), "Fabrício Liberato",
				"111.222.333-44", LocalDate.now(), endereco));
		DAO.commit();
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
		return usuarioDAO.realizaLogin(user, geraHashBytes(password));
	}
	
	public static Consulta solicitaConsulta (LocalDateTime dataHorario, String especialidade, Paciente p) throws Exception {
		
		DAO.begin();
		
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (dataHorario.toLocalDate().compareTo(amanha) < 0)
			throw new Exception("Consultas nao sao agendadas para o dia atual!");
		if (dataHorario.getHour() < 8 || dataHorario.getHour() > 16)
			throw new Exception("Atendimento apenas das 08:00 as 16:00 horas!");
		
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade e = especialidadeDAO.consultaEspecialidade(especialidade);
		
		Consulta consulta = new Consulta(dataHorario, p, false, e);
		p.add(consulta);
		
		ConsultaDAO consultaDAO = new ConsultaDAO();
		consultaDAO.create(consulta);
		
		PacienteDAO pacienteDAO = new PacienteDAO();
		pacienteDAO.update(p);
		
		DAO.commit();
		return consulta;
	}
	
	public static Consulta confirmaConsulta (Consulta consulta, Secretario secretario) throws Exception {
		DAO.begin();
		LocalDate amanha = LocalDateTime.now().plusDays(1).toLocalDate();
		if (consulta.getdataHorario().toLocalDate().compareTo(amanha) > 0) {
			consulta.setSecretario(secretario);
			consulta.setConfirmado(true);
			EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
			ArrayList<Medico> medicos = especialidadeDAO.consultaMedicoPorEspecialidade(consulta.getEspecialidade());
			for (Medico m : medicos) {
				boolean ocupado = false;
				for (Consulta c : m.getConsultas())
					if (c.getdataHorario().compareTo(consulta.getdataHorario()) == 0) {
						ocupado = true;
						break;
					}
				if (!ocupado) {
					consulta.setMedico(m);
					UsuarioDAO usuarioDAO = new UsuarioDAO();
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
	
}
