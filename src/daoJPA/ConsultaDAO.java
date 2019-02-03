package daoJPA;


import java.util.List;

import javax.persistence.Query;

import modelo.Consulta;

public class ConsultaDAO extends DAO<Consulta> {

//	public List<Consulta> consultasParaConfirmacao () {
//		Query q = manager.createQuery("SELECT c FROM Consulta c WHERE confirmado = false");
//		Query q = manager.query();
//		q.constrain(Consulta.class);
//		q.constrain((Evaluation) candidato -> {
//			// TODO Auto-generated method stub
//			Consulta consulta = (Consulta) candidato.getObject();
//			if (!consulta.isConfirmado())
//				if (consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0) {
//					candidato.include(true);
//					return;
//				}					
//			candidato.include(false);
//		});
////		q.constrain(new Evaluation() {
////			
////			@Override
////			public void evaluate(Candidate candidato) {
////				// TODO Auto-generated method stub
////				Consulta consulta = (Consulta) candidato.getObject();
////				System.out.println(consulta);
////				candidato.include(consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0);
////			}
////		});
//		return q.execute();
//		return q.getResultList();
//	}
//	
	public List<Consulta> consultasSolicitadasPorPaciente (String cpf) {
		Query q = manager.createQuery("SELECT c FROM Paciente p JOIN Consulta c WHERE p.cpf = :cpf and c.confirmado = false");
		q.setParameter("cpf", cpf);
		return q.getResultList();
	}
//	
//	public List<Consulta> consultasRealizadas (Usuario usuario) {
//		// TODO
//		Query q = manager.query();
//		q.constrain(Consulta.class);
//		q.constrain((Evaluation) candidato -> {
//			// TODO Auto-generated method stub
//			Consulta consulta = (Consulta) candidato.getObject();
//			if (usuario instanceof Paciente) {
//				if (!consulta.getPaciente().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			} else if (usuario instanceof Secretario) { 
//				if (!consulta.getSecretario().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			} else if (usuario instanceof Medico) {
//				if (!consulta.getMedico().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			}
//			if (consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) < 0) {
//				if (consulta.isConfirmado()) {
//					candidato.include(true);
//					return;
//				}
//			}
//			candidato.include(false);
//		});
//		return q.execute();
//	}
//
//
//	public List<Consulta> consultasARealizar (Usuario usuario) {
//		// TODO
//		Query q = manager.query();
//		q.constrain(Consulta.class);
//		q.constrain((Evaluation) candidato -> {
//			// TODO Auto-generated method stub
//			Consulta consulta = (Consulta) candidato.getObject();
//			if (usuario instanceof Paciente) {
//				if (!consulta.getPaciente().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			} else if (usuario instanceof Secretario) { 
//				if (!consulta.getSecretario().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			} else if (usuario instanceof Medico) {
//				if (!consulta.getMedico().getCpf().equals(usuario.getCpf())) {
//					candidato.include(false);
//					return;
//				}
//			}
//			if (consulta.getdataHorario().toLocalDate().compareTo(LocalDate.now()) > 0) {
//				if (consulta.isConfirmado()) {
//					candidato.include(true);
//					return;
//				}
//			}
//			candidato.include(false);
//		});
//		return q.execute();
//	}
//	
//	public Consulta pesquisaNomeHorario (String horario, String primeiroNome) {
//		Query q = manager.query();
//		q.constrain(Consulta.class);
//		q.constrain((Evaluation) candidate -> {
//			Consulta c = (Consulta) candidate.getObject();
//			if (c.getPaciente().getNome().contains(primeiroNome))
//				if (c.getdataHorario().toString().equals(horario)) {
//					candidate.include(true);
//					return;
//				}
//			candidate.include(false);
//		});
//		List<Consulta> consultas = q.execute();
//		if(!consultas.isEmpty())
//			return consultas.get(0);
//		return null;
//	}
	
}
