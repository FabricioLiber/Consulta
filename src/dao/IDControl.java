package dao;

import java.util.List;
import java.util.TreeMap;

import com.db4o.ObjectContainer;
import com.db4o.events.CancellableObjectEventArgs;
import com.db4o.events.CommitEventArgs;
import com.db4o.events.Event4;
import com.db4o.events.EventListener4;
import com.db4o.events.EventRegistry;
import com.db4o.events.EventRegistryFactory;

public class IDControl {

	// Faz a geração automatica de IDs para qualquer classe que implementa
		// a interface IDInterface
		protected static ObjectContainer manager;
		private static boolean gerounovoid;		
		private static TreeMap<String,Integer> ids;


		//	=============================================	
		public static void registrarManager(ObjectContainer man){
			manager = man;
			EventRegistry eventRegistry = EventRegistryFactory.forObjectContainer(manager);

			// PRE-PERSIST - antes de persistir
			eventRegistry.creating().addListener(new EventListener4<CancellableObjectEventArgs>() {
				public void onEvent(Event4<CancellableObjectEventArgs> event4, CancellableObjectEventArgs args) {

					// verificar se o objeto persistido implementa a interface IDInterface
					Object objeto = args.object();
					if(objeto instanceof IDInterface){
						String nomedaclasse = objeto.getClass().getName();
						// verifica se o treemap nao se encontra na memoria
						if (ids==null) {
							//localiza o treemap no banco
							List<TreeMap<String,Integer>> resultados = manager.queryByExample(new TreeMap<String,Integer>());
							if(resultados.size()==0)
								ids = new TreeMap<String,Integer>(); // cria na memoria
							else
								ids = (TreeMap<String, Integer>) resultados.get(0); // carrega
						}
						//localizar e incrementar o ultimo id da classe do objeto persistido
						int novoid;
						if (ids.get(nomedaclasse) == null)
							novoid = 1;  			//primeiro id gerado
						else
							novoid = ids.get(nomedaclasse)+1;  // incrementa ultimo id
						ids.put(nomedaclasse, novoid);  // guarda novo id 

						// inserir o novo id  no objeto que está sendo persistido
						((IDInterface) objeto).setId(novoid); 
						gerounovoid = true;
						//System.out.println("--->gerando id="+novoid+" para a classe="+nomedaclasse);
					}
				}});

			// POST-COMMIT  - apos commit
			eventRegistry.committed().addListener(new EventListener4<CommitEventArgs>() {
				public void onEvent(Event4<CommitEventArgs> commitEventArgsEvent4, CommitEventArgs args) {
					//salvar os ids alterados no banco
					if (gerounovoid) {
						gerounovoid = false;
						manager.store(ids);
						ids=null;
						manager.ext().purge(ids);  //limpar cache do manager
					}
				}});     		
	}
}
