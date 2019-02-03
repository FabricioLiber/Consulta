package daoJPA;


import javax.persistence.Query;

import modelo.Especialidade;

public class EspecialidadeDAO extends DAO<Especialidade> {
	
	
	public Especialidade consultaEspecialidade (String descricao) {
		Query q = manager.createQuery("SELECT e FROM Especialidade e WHERE e.descricao = :descricao");
		q.setParameter("descricao", descricao);
		return (Especialidade) q.getSingleResult();
	}	

}
