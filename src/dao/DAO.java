package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import modelo.Consulta;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Especialidade;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class DAO<T> implements InterfaceDAO<T>{

	protected static ObjectContainer manager; 
	public static void open () {
		if (manager == null)
			abrirBancoLocal();
	}
	
	private static void abrirBancoLocal () {
		
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().messageLevel(0);
		
		// Configurando as classes
		
		
		// Class Endereco
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnUpdate(false);
		config.common().objectClass(Endereco.class).cascadeOnDelete(false);
		
		// Class Usuario
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnUpdate(false);
		config.common().objectClass(Usuario.class).cascadeOnDelete(false);
		
		// Class Paciente
		config.common().objectClass(Paciente.class).cascadeOnActivate(true);
		config.common().objectClass(Paciente.class).cascadeOnUpdate(false);
		config.common().objectClass(Paciente.class).cascadeOnDelete(false);

		// Class Secretario
		config.common().objectClass(Secretario.class).cascadeOnActivate(true);
		config.common().objectClass(Secretario.class).cascadeOnUpdate(false);
		config.common().objectClass(Secretario.class).cascadeOnDelete(false);
		
		// Class Medico
		config.common().objectClass(Medico.class).cascadeOnActivate(true);
		config.common().objectClass(Medico.class).cascadeOnUpdate(false);
		config.common().objectClass(Medico.class).cascadeOnDelete(false);
		
		// Class Convenio
		config.common().objectClass(Convenio.class).cascadeOnActivate(true);
		config.common().objectClass(Convenio.class).cascadeOnUpdate(false);
		config.common().objectClass(Convenio.class).cascadeOnDelete(false);
		
		// Class Consulta
		config.common().objectClass(Consulta.class).cascadeOnActivate(true);
		config.common().objectClass(Consulta.class).cascadeOnUpdate(false);
		config.common().objectClass(Consulta.class).cascadeOnDelete(false);
		
		// Class Especialidade
		config.common().objectClass(Especialidade.class).cascadeOnActivate(true);
		config.common().objectClass(Especialidade.class).cascadeOnUpdate(false);
		config.common().objectClass(Especialidade.class).cascadeOnDelete(false);
		
		// Indexacao de Atributos
		config.common().objectClass(Consulta.class).objectField("dataConsulta").indexed(true);
		
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
	}
	
	private static void abrirBancoServidor () {
		ClientConfiguration config = Db4oClientServer.newClientConfiguration();
		config.common().messageLevel(0);
		
		// Configurando as classes
		
		// Class Endereco
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(false);
		
		// Class Usuario
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);
		config.common().objectClass(Usuario.class).cascadeOnDelete(false);
		
		// Class Paciente
		config.common().objectClass(Paciente.class).cascadeOnActivate(true);
		config.common().objectClass(Paciente.class).cascadeOnUpdate(true);
		config.common().objectClass(Paciente.class).cascadeOnDelete(false);

		// Class Secretario
		config.common().objectClass(Secretario.class).cascadeOnActivate(true);
		config.common().objectClass(Secretario.class).cascadeOnUpdate(true);
		config.common().objectClass(Secretario.class).cascadeOnDelete(false);
		
		// Class Medico
		config.common().objectClass(Medico.class).cascadeOnActivate(true);
		config.common().objectClass(Medico.class).cascadeOnUpdate(true);
		config.common().objectClass(Medico.class).cascadeOnDelete(false);
		
		// Class Convenio
		config.common().objectClass(Convenio.class).cascadeOnActivate(true);
		config.common().objectClass(Convenio.class).cascadeOnUpdate(true);
		config.common().objectClass(Convenio.class).cascadeOnDelete(false);
		
		// Class Consulta
		config.common().objectClass(Consulta.class).cascadeOnActivate(true);
		config.common().objectClass(Consulta.class).cascadeOnUpdate(true);
		config.common().objectClass(Consulta.class).cascadeOnDelete(false);
		
		// Class Especialidade
		config.common().objectClass(Especialidade.class).cascadeOnActivate(true);
		config.common().objectClass(Especialidade.class).cascadeOnUpdate(true);
		config.common().objectClass(Especialidade.class).cascadeOnDelete(false);
		
		// Indexacao de Atributos
		config.common().objectClass(Consulta.class).objectField("dataConsulta").indexed(true);
		
		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
		if (ip==null || ip.isEmpty())	{
			System.out.println("ip invalido");
			System.exit(0);
		}
		
		manager = Db4oClientServer.openClient(config, ip, 34000, "usuario1", "senha1");
	}
	
	public static void close () {
		if (manager != null) {
			manager.close();
			manager = null;
		}			
	}
	
	@Override
	public void create(T object) {
		manager.store(object);
	}

	@Override
	public T update(T object) {
		manager.store(object);
		return object;
	}

	@Override
	public void delete(T object) {
		manager.delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> readAll() {
		Class<T> type = (Class<T>) ( (ParameterizedType) this.getClass().getGenericSuperclass()).
				getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}
	
	
	// Metodos usados para transacao
	
	public static void begin () {

	}
	
	public static void commit () {
		manager.commit();
	}
	
		public static void rollback () {
		manager.rollback();
	}
}
