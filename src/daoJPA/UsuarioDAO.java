package daoJPA;

import javax.persistence.Query;

import modelo.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	
	public Usuario verificaUsuario (String user, String password) {
		
		Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password");
		q.setParameter("username", user);
		q.setParameter("password", password);
		return (Usuario) q.getSingleResult();	
		
	}
	
	
	public Usuario readByCpf (String cpf) {
		
		Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf");
		q.setParameter("cpf", cpf);
		return (Usuario) q.getSingleResult();
		
	}
	
}