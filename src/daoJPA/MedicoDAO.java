package daoJPA;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import modelo.Especialidade;
import modelo.Medico;

public class MedicoDAO extends DAO<Medico> {
	
	public List<Medico> especialistasDisponiveis (LocalDateTime horario, String especialidade) {
//		Query q = manager.createQuery("SELECT m FROM Especialidade e JOIN Medico m JOIN Consulta c WHERE e.descricao = :especialidade and c.dataHorario <> :dataHorario");
		Query q = manager.createQuery("SELECT m FROM Especialidade e JOIN Medico m WHERE e.descricao = :especialidade");
		q.setParameter("especialidade", especialidade);
//		q.setParameter("dataHorario", horario);
		return q.getResultList();
	}
	
	public List<Medico> consultaMedicosPorEspecialidade (String descricao) {
		Query q = manager.createQuery("SELECT m FROM Especialidade e JOIN e.medicos m WHERE e.descricao = :descricao");
		q.setParameter("descricao", descricao);
		return q.getResultList();		
	}

}
