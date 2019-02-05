package daoJPA;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	
	public Usuario verificaUsuario (String user, String password) throws Exception {
		
		Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password");
		q.setParameter("username", user);
		q.setParameter("password", password);
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}
	
	
	public Usuario readByCpf (String cpf) {
		
		Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf");
		q.setParameter("cpf", cpf);
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		
	}
	
}