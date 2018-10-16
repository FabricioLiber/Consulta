package aplicacao;
/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 * teste cliente managerservidor com db4o
 */
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

public class Servidor implements Runnable{
	private ObjectServer managerservidor;
	private Thread thread ;
	
	public Servidor() {	

		if(new File("bancoservidor.db4o").exists()){
			Scanner teclado = new Scanner(System.in);
			String opcao = " ";
			do{
				System.out.print("Você deseja apagar o banco de dados? (s/n) Opcão: ");
				opcao = teclado.nextLine().toUpperCase();
			}while(opcao.compareTo("S") != 0 && opcao.compareTo("N") != 0);
			if(opcao.compareTo("S")==0){
				new File ("bancoservidor.db4o").delete(); //apagar o arquivo
				System.out.println("banco de dados foi apagado");
			}
			teclado.close();
		}

		
		ServerConfiguration config = Db4oClientServer.newServerConfiguration() ;
		config.common().messageLevel(0);   //0,1,2,3,4
		managerservidor = Db4oClientServer.openServer(config, "bancoservidor.db4o",34000);

		// cadastrar usuarios
		managerservidor.grantAccess("usuario1","senha1");
		managerservidor.grantAccess("usuario2","senha2");
		managerservidor.grantAccess("usuario3","senha3");
		managerservidor.grantAccess("usuario4","senha4");
		managerservidor.grantAccess("usuario5","senha5");
		managerservidor.grantAccess("usuario6","senha6");
		managerservidor.grantAccess("usuario7","senha7");
		managerservidor.grantAccess("usuario8","senha8");
		managerservidor.grantAccess("usuario9","senha9");
		managerservidor.grantAccess("usuario10","senha10");
		managerservidor.grantAccess("usuario11","senha11");
		managerservidor.grantAccess("usuario12","senha12");
		managerservidor.grantAccess("usuario13","senha13");
		managerservidor.grantAccess("usuario14","senha14");
		managerservidor.grantAccess("usuario15","senha15");

		long databaseSize = managerservidor.ext().objectContainer().ext().systemInfo().totalSize();
		System.out.println("Database size: "+databaseSize);
		System.out.println("Servidor inicializado com sucesso: ");
		thread = new Thread(this);
		thread.start();		
	}

	public void run() {
			System.out.println("\nservidor pronto! ");
			System.out.println("aguardando requisição....  ");
			JOptionPane.showMessageDialog(null, "execute a aplicação cliente \ntecle ok para encerrar o managerservidor");
			close();
	}
	
	public void close() {
		System.out.println("\nFechando o managerservidor. ");
		managerservidor.close();
		thread.interrupt();	
	}
	
	public static void main(String[] args) {
		new Servidor();	
	}


}
