package daoDB4O;

import java.util.List;

import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	public Usuario verificaUsuario (String user, byte [] password) {

		Query q = manager.query();
		q.constrain(Usuario.class);
//		q.descend("user").constrain(user);
//		List<Usuario> usuarios = q.execute();
//		if (!usuarios.isEmpty())
//			for (int i = 0; i < usuarios.size(); i++)
//				if (usuarios.get(i).getUser().equals(user)) {
//					for (int j = 0; j < password.length; j++)
//						if (password[j] == usuarios.get(i).getPassword()[j])
//							continue;
//						else
//							return null;
//					return usuarios.get(i);
//				}
//		return null;
		
		q.constrain((Evaluation) candidate -> {
			// TODO Auto-generated method stub
			Usuario u = (Usuario) candidate.getObject();
			if (u.getUser().equals(user)) {
				for (int j = 0; j < password.length; j++) {
					if (password[j] == u.getPassword()[j]) {
						continue;
				}else {
						candidate.include(false);
						return;
					}
				}
				candidate.include(true);
				return;
			}
			candidate.include(false);
			
		});

		List<Usuario> usuarios = q.execute();
		if (usuarios.size() > 0)
			return usuarios.get(0);	
		return null;
	}
	
	public Usuario readByCpf (String cpf) {
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("cpf").constrain(cpf);
		List<Usuario> usuarios = q.execute();
		System.out.println(usuarios.get(0));
		if (usuarios.size() > 0)
			return usuarios.get(0);
		return null;
	}
	
}