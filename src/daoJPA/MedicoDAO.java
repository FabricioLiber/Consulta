package daoJPA;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.Consulta;
import modelo.Medico;

public class MedicoDAO extends DAO<Medico> {
	
	public List<Medico> especialistasDisponiveis (LocalDateTime horario, String especialidade) {
//		Query q = manager.createQuery("SELECT m FROM Especialidade e JOIN Medico m WHERE e.descricao = :especialidade and c.dataHorario <> :dataHorario");
//		Query q = manager.createQuery("SELECT m FROM Especialidade e JOIN e.medicos m WHERE e.descricao = :especialidade");
		Query q = manager.createQuery("SELECT m FROM Medico m JOIN m.especialidades e WHERE e.descricao = :especialidade");
		q.setParameter("especialidade", especialidade);
		List<Medico> medicos = q.getResultList();
		List<Medico> medicosSelecionados = new ArrayList<>();
		medicosSelecionados.addAll(medicos);
		if (!medicos.isEmpty())
			for (Medico m : medicos)
				if (!m.getConsultas().isEmpty())
					for (Consulta c : m.getConsultas())
						if (c.getdataHorario().getDayOfYear() == horario.getDayOfYear())
							if (c.getdataHorario().getHour() == horario.getHour())
								if (c.getdataHorario().getMinute() == horario.getMinute())
									medicosSelecionados.remove(m);
		return medicosSelecionados;
	}
	
	public List<Medico> consultaMedicosPorEspecialidade (String descricao) {
		Query q = manager.createQuery("SELECT m FROM Medico m JOIN m.especialidades e WHERE e.descricao = :descricao");
		q.setParameter("descricao", descricao);
		return q.getResultList();		
	}

}
