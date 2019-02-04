package daoJPA;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Query;

import modelo.Consulta;
import modelo.Usuario;

public class ConsultaDAO extends DAO<Consulta> {

	public List<Consulta> consultasParaConfirmacao () {
		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE c.confirmado = false and c.dataHorario > :dataHorario");
		q.setParameter("dataHorario", LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(8, 0)));
		return q.getResultList();
	}
	
	public List<Consulta> consultasSolicitadasPorPaciente (String cpf) {
		Query q = manager.createQuery("SELECT c FROM Paciente p JOIN Consulta c WHERE p.cpf = :cpf and c.confirmado = false");
		q.setParameter("cpf", cpf);
		return q.getResultList();
	}
	
	public List<Consulta> consultasRealizadas (Usuario usuario) {
		Query q = manager.createQuery("SELECT c FROM Usuario u JOIN Consulta c WHERE c.confirmado = true and c.dataHorario < :dataHorario");
		q.setParameter("dataHorario", LocalDateTime.now());
		return q.getResultList();
	}


	public List<Consulta> consultasARealizar (Usuario usuario) {
		Query q = manager.createQuery("SELECT c FROM Usuario u JOIN Consulta c WHERE c.confirmado = true and c.dataHorario >= :dataHorario");
		q.setParameter("dataHorario", LocalDateTime.now());
		return q.getResultList();
	}
	
	public Consulta pesquisaNomeHorario (LocalDateTime horario, String primeiroNome) {
		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE c.paciente.nome = :nome and c.dataHorario = :horario");
		
		q.setParameter("nome", primeiroNome);
		q.setParameter("horario", horario);
		return (Consulta) q.getSingleResult();
	}
	
}
