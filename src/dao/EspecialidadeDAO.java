package dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Consulta;
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
	
//		for (Medico m : medicos) {
//			boolean ocupado = false;
//			for (Consulta c : m.getConsultas())
//				if (c.getdataHorario().compareTo(consulta.getdataHorario()) == 0) {
//					ocupado = true;
//					break;
//				}
//			if (!ocupado) {
//				consulta.setMedico(m);
//				UsuarioDAO usuarioDAO = new UsuarioDAO();
//				consulta.getMedico().add(consulta);
//				usuarioDAO.update(consulta.getMedico());
//				consulta.getSecretario().add(consulta);
//				usuarioDAO.update(consulta.getSecretario());
//				ConsultaDAO consultaDAO = new ConsultaDAO();
//				consultaDAO.update(consulta);
//				DAO.commit();
//				return consulta;
//			}					
//		}
		
		Query q = manager.query();
		q.constrain(Especialidade.class);
		q.descend("descricao").constrain(e.getDescricao());
		List<Especialidade> especialidades = q.execute();
		if (!especialidades.isEmpty())
			return especialidades.get(0).getMedicos();
		return null;
		
	}
	

}
