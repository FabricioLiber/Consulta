package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Consulta;

public class ConsultaDAO extends DAO<Consulta> {

	public List<Consulta> consultasParaConfirmacao () {
		Query q = manager.query();
		q.constrain(Consulta.class);
		q.descend("confirmado").constrain(false);
		List<Consulta> consultas = q.execute();
		List<Consulta> consultasParaConfirmarValidas = new ArrayList<Consulta>();
		LocalDate amanha = LocalDate.now().plusDays(1);
		for (Consulta c: consultas)			
			if (c.getdataHorario().toLocalDate().compareTo(amanha) > 0)
				consultasParaConfirmarValidas.add(c);
		return consultasParaConfirmarValidas;
	}
	
	
}
