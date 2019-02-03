package daoJPA;

import javax.persistence.Query;

import modelo.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	public Usuario verificaUsuario (String user, String password) {
		
		
			Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password");
			q.setParameter("username", user);
			q.setParameter("password", password);
			return (Usuario) q.getSingleResult();		
//
//		Query q = manager.query();
//		q.constrain(Usuario.class);
////		q.descend("user").constrain(user);
////		List<Usuario> usuarios = q.execute();
////		if (!usuarios.isEmpty())
////			for (int i = 0; i < usuarios.size(); i++)
////				if (usuarios.get(i).getUser().equals(user)) {
////					for (int j = 0; j < password.length; j++)
////						if (password[j] == usuarios.get(i).getPassword()[j])
////							continue;
////						else
////							return null;
////					return usuarios.get(i);
////				}
////		return null;
//		
//		q.constrain((Evaluation) candidate -> {
//			// TODO Auto-generated method stub
//			Usuario u = (Usuario) candidate.getObject();
//			if (u.getUser().equals(user)) {
//				for (int j = 0; j < password.length; j++) {
//					if (password[j] == u.getPassword()[j]) {
//						continue;
//				}else {
//						candidate.include(false);
//						return;
//					}
//				}
//				candidate.include(true);
//				return;
//			}
//			candidate.include(false);
//			
//		});
//
//		List<Usuario> usuarios = q.execute();
//		if (usuarios.size() > 0)
//			return usuarios.get(0);	
//		return null;
	}
	
	public Usuario readByCpf (String cpf) {
		Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf");
		q.setParameter("username", cpf);
		return (Usuario) q.getSingleResult();
	}
	
}