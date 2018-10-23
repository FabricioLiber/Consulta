package dao;

import java.time.LocalDateTime;
import java.util.List;

import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Consulta;
import modelo.Especialidade;
import modelo.Medico;

public class MedicoDAO extends DAO<Medico> {
	
	public List<Medico> especialistasDisponiveis (LocalDateTime horario, String especialidade) {
		
		Query q = manager.query();
		q.constrain((Evaluation) candidato -> {
			// TODO Auto-generated method stub
			Medico medico = (Medico) candidato.getObject();
			for (Especialidade e : medico.getEspecialidades()) {
				if (!e.getDescricao().equals(especialidade)) {
					candidato.include(false);
					return;
				} else {
					for (Consulta c : medico.getConsultas())
						if (c.getdataHorario().toLocalDate().compareTo(horario.toLocalDate()) == 0)
							if (c.getdataHorario().getHour() == horario.getHour())
								if (c.getdataHorario().getMinute() == horario.getMinute()) {
									candidato.include(false);								
									return;
								}
				}
			}					
			candidato.include(true);
		});
		return q.execute();
	}
	
	public List<Medico> consultaMedicosPorEspecialidade (String especialidade) {
		
		Query q = manager.query();
		q.constrain(Medico.class);
		q.descend("especialidade").descend("descricao").constrain(especialidade);
//		q.constrain((Evaluation) candidato -> {
//			// TODO Auto-generated method stub
//			Medico m = (Medico) candidato.getObject();
//			for (Especialidade e : m.getEspecialidades()) {
//					candidato.include(e.getDescricao().equals(especialidade));		
//					return;
//				}
//		});
		return q.execute();
		
	}

}
