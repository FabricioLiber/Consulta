package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Consulta;
import modelo.Medico;

public class TelaConfirmaConsulta extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 403, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, -11, 379, 352);
		contentPane.add(panel);
		
		JLabel lblConfirmaoDeConsulta = new JLabel("Confirma\u00E7\u00E3o de Consulta");
		lblConfirmaoDeConsulta.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblConfirmaoDeConsulta.setBounds(51, 11, 275, 50);
		panel.add(lblConfirmaoDeConsulta);
		
		JLabel label_1 = new JLabel("Consultas para confirmação: ");
		label_1.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label_1.setBounds(38, 89, 307, 26);
		panel.add(label_1);
		
		JComboBox<String> comboBoxConsultas = new JComboBox<String>();
		comboBoxConsultas.setFont(new Font("Rockwell", Font.PLAIN, 22));
		comboBoxConsultas.setBounds(39, 130, 287, 32);
		panel.add(comboBoxConsultas);
		for (Consulta c : Fachada.listaConsultasParaConfirmacao())
			comboBoxConsultas.addItem(c.getPaciente().getNome().split("0")[0]+ " " + c.getdataHorario().toString());
		
		JLabel lblMdicos = new JLabel("M\u00E9dicos:");
		lblMdicos.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblMdicos.setBounds(38, 168, 167, 32);
		panel.add(lblMdicos);
		
		JComboBox<String> comboBoxMedicos = new JComboBox<String>();
		comboBoxMedicos.setFont(new Font("Rockwell", Font.PLAIN, 22));
		comboBoxMedicos.setBounds(39, 211, 145, 32);
		panel.add(comboBoxMedicos);
		
		JButton button = new JButton("Solicitar");
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(117, 286, 145, 35);
		panel.add(button);
		Fachada.inicializar();
//		for (Medico m : Fachada.especialistasDisponiveisPorHorario(consulta.getHorario(), consulta.getEspecialidade()))
//			comboBoxMedicos.addItem(m.getNome());
		
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
