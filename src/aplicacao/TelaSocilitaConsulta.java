package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DateTimePicker;

import fachada.Fachada;
import modelo.Consulta;
import modelo.Especialidade;

public class TelaSocilitaConsulta extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> listaEspecialidades;
	private DateTimePicker dateTime;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaSocilitaConsulta frame = new TelaSocilitaConsulta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaSocilitaConsulta() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel label_1 = new JLabel("Solicitação de Consulta");
		label_1.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label_1.setBounds(80, 11, 241, 50);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("Data e Hora: ");
		label.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label.setBounds(38, 89, 146, 26);
		contentPane.add(label);
		
		dateTime = new DateTimePicker();
		dateTime.setFont(new Font("Rockwell", Font.PLAIN, 14));
		dateTime.setBounds(38, 125, 300, 32);
		contentPane.add(dateTime);
		
		JLabel label_2 = new JLabel("Especialidade: ");
		label_2.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label_2.setBounds(38, 168, 167, 32);
		contentPane.add(label_2);
		
		listaEspecialidades = new JComboBox<>();
		listaEspecialidades.setFont(new Font("Rockwell", Font.PLAIN, 22));
		listaEspecialidades.setBounds(39, 211, 145, 32);
		contentPane.add(listaEspecialidades);
		Fachada.inicializar();
		listaEspecialidades.removeAllItems();
		for (Especialidade e : Fachada.listaDeEspecialidades())
			listaEspecialidades.addItem(e.getDescricao());
		
		JButton button = new JButton("Solicitar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime dateTimePicker = dateTime.getDateTimeStrict();
				try {
					Consulta c = Fachada.solicitaConsulta(dateTimePicker, String.valueOf(listaEspecialidades.getSelectedItem()));
					JOptionPane.showMessageDialog(contentPane, "Solicitação Concluída", "Solicitação de Consulta", JOptionPane.INFORMATION_MESSAGE);
					TelaPaciente telaPaciente = new TelaPaciente();
					telaPaciente.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(117, 286, 145, 35);
		contentPane.add(button);
	}

}
