package daoJPA;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class ConsultaDAO extends DAO<Consulta> {

	public List<Consulta> consultasParaConfirmacao () {
		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE c.confirmado = false and c.dataHorario > :dataHorario");
		q.setParameter("dataHorario", LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(8, 0)));
		return q.getResultList();
	}
	
	public List<Consulta> consultasSolicitadasPorPaciente (String cpf) {
		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE c.paciente.cpf = :cpf and c.confirmado = false");
		q.setParameter("cpf", cpf);
		return q.getResultList();
	}
	
	public List<Consulta> consultasRealizadas (Usuario usuario) throws Exception {
		Query q = null;
		if (usuario instanceof Paciente)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.paciente.cpf = :cpf and c.confirmado = true and c.dataHorario < :dataHorario");
		else if (usuario instanceof Medico)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.medico.cpf = :cpf and c.confirmado = true and c.dataHorario < :dataHorario");			
		else if (usuario instanceof Secretario)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.secretario.cpf = :cpf and c.confirmado = true and c.dataHorario < :dataHorario");
		else
			throw new Exception("Classificacao do usuario nao informada!");
		q.setParameter("dataHorario", LocalDateTime.now());
		q.setParameter("cpf", usuario.getCpf());
		return q.getResultList();
	}


	public List<Consulta> consultasARealizar (Usuario usuario) throws Exception {
		Query q = null;
		if (usuario instanceof Paciente)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.paciente.cpf = :cpf and c.confirmado = true and c.dataHorario >= :dataHorario");
		else if (usuario instanceof Medico)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.medico.cpf = :cpf and c.confirmado = true and c.dataHorario >= :dataHorario");			
		else if (usuario instanceof Secretario)
			q = manager.createQuery("SELECT c FROM Consulta c WHERE c.secretario.cpf = :cpf and c.confirmado = true and c.dataHorario >= :dataHorario");
		else
			throw new Exception("Classificacao do usuario nao informada!");
		q.setParameter("dataHorario", LocalDateTime.now());
		q.setParameter("cpf", usuario.getCpf());
		return q.getResultList();
	}
	
	public Consulta pesquisaNomeHorario (LocalDateTime horario, String primeiroNome) {
		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE c.paciente.nome LIKE :nome and c.dataHorario = :horario");
		
		q.setParameter("nome", primeiroNome+"%");
		q.setParameter("horario", horario);
		try {
			return (Consulta) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
}
