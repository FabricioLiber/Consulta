package daoJPA;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Convenio;

public class ConvenioDAO extends DAO<Convenio>{

	
	public Convenio consultaConvenio (String descricao) {
		Query q = manager.createQuery("SELECT c FROM Convenio c WHERE c.descricao = :descricao");
		q.setParameter("descricao", descricao);
		try {
			return (Convenio) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	
	}
	
}
