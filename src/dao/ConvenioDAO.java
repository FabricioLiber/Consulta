package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Convenio;

public class ConvenioDAO extends DAO<Convenio>{

	
	public Convenio consultaConvenio (String descricao) {
		Query q = manager.query();
		q.constrain(Convenio.class);
		q.descend("descricao").constrain(descricao);
		List<Convenio> convenios = q.execute();
		if (!convenios.isEmpty())
			return convenios.get(0);
		return null;
	}
	
}
