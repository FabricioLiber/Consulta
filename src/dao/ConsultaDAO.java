package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class ConsultaDAO extends DAO<Consulta> {

	public List<Consulta> consultasParaConfirmacao () {
		Query q = manager.query();
		q.constrain(Consulta.class);
		q.constrain((Evaluation) candidato -> {
			// TODO Auto-generated method stub
			Consulta consulta = (Consulta) candidato.getObject();
			if (!consulta.isConfirmado())
				if (consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0) {
					candidato.include(true);
					return;
				}					
			candidato.include(false);
		});
//		q.constrain(new Evaluation() {
//			
//			@Override
//			public void evaluate(Candidate candidato) {
//				// TODO Auto-generated method stub
//				Consulta consulta = (Consulta) candidato.getObject();
//				System.out.println(consulta);
//				candidato.include(consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0);
//			}
//		});
		return q.execute();
	}
	
	public List<Consulta> consultasSolicitadasPorPaciente (String cpf) {
		Query q = manager.query();
		q.constrain(Consulta.class);
		q.descend("paciente").descend("cpf").constrain(cpf);
		q.descend("confirmado").constrain(false);
		return q.execute();
	}
	
	public List<Consulta> consultasRealizadas (Usuario usuario) {
		// TODO
		Query q = manager.query();
		q.constrain(Consulta.class);
		q.constrain(new Evaluation() {
			
			@Override
			public void evaluate(Candidate candidato) {
				// TODO Auto-generated method stub
				Consulta consulta = (Consulta) candidato.getObject();
				if (usuario instanceof Paciente)
					if (!consulta.getPaciente().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
				else if (usuario instanceof Secretario) 
					if (!consulta.getSecretario().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
				else if (usuario instanceof Medico)
					if (!consulta.getMedico().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
				if (consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) < 0)
					if (consulta.isConfirmado())
						candidato.include(true);
				candidato.include(false);
			}
		});
		return q.execute();
	}


	public List<Consulta> consultasARealizar (Usuario usuario) {
		// TODO
		Query q = manager.query();
		q.constrain(Consulta.class);
		q.constrain(new Evaluation() {
			
			@Override
			public void evaluate(Candidate candidato) {
				// TODO Auto-generated method stub
				Consulta consulta = (Consulta) candidato.getObject();
				if (usuario instanceof Paciente)
					if (!consulta.getPaciente().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
				else if (usuario instanceof Secretario) 
					if (!consulta.getSecretario().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
				else if (usuario instanceof Medico)
					if (!consulta.getMedico().getCpf().equals(usuario.getCpf()))
						candidato.include(false);
					if (consulta.isConfirmado())
						candidato.include(true);
				candidato.include(false);
			}
		});
		return q.execute();
	}
	
}
