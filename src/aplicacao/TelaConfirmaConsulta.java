package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Consulta;

public class TelaConfirmaConsulta extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> listaPacientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConfirmaConsulta frame = new TelaConfirmaConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConfirmaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		listaPacientes = new JComboBox<>();
		listaPacientes.setFont(new Font("Rockwell", Font.PLAIN, 22));
		listaPacientes.setBounds(39, 211, 145, 32);
		contentPane.add(listaPacientes);
		Fachada.inicializar();
		for (Consulta c : Fachada.listaConsultasParaConfirmacao())
			listaPacientes.addItem(c.getPaciente().getNome());
		
//		listaEspecialidades = new JComboBox<>();
//		listaEspecialidades.setFont(new Font("Rockwell", Font.PLAIN, 22));
//		listaEspecialidades.setBounds(39, 211, 145, 32);
//		contentPane.add(listaEspecialidades);
//		Fachada.inicializar();
//		listaEspecialidades.removeAllItems();
//		for (Especialidade e : Fachada.listaDeEspecialidades())
//			listaEspecialidades.addItem(e.getDescricao());
	}

}
