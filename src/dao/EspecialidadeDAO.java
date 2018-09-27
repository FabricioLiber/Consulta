package dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Especialidade;
import modelo.Medico;

public class EspecialidadeDAO extends DAO<Especialidade> {
	
	
	public Especialidade consultaEspecialidade (String descricao) {
		Query q = manager.query();
		q.constrain(Especialidade.class);
		q.descend("descricao").constrain(descricao);
		List<Especialidade> especialidades = q.execute();
		if (!especialidades.isEmpty())
			return especialidades.get(0);
		return null;
	}
	
	public ArrayList<Medico> consultaMedicosPorEspecialidade (Especialidade e) {
	
		Query q = manager.query();
		q.constrain(Especialidade.class);
		q.descend("descricao").constrain(e.getDescricao());
		List<Especialidade> especialidades = q.execute();
		if (!especialidades.isEmpty())
			return especialidades.get(0).getMedicos();
		return null;
		
	}
	

}
