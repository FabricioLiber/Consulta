package daoJPA;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Especialidade;

public class EspecialidadeDAO extends DAO<Especialidade> {
	
	
	public Especialidade consultaEspecialidade (String descricao) {
		Query q = manager.createQuery("SELECT e FROM Especialidade e WHERE e.descricao = :descricao");
		q.setParameter("descricao", descricao);
		try {
			return (Especialidade) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}	

}
