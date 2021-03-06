package aplicacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Consulta;
import modelo.Medico;

public class TelaConfirmaConsulta extends JFrame {

	private JPanel contentPane;
	private Consulta consulta;

	/**
	 * Create the frame.
	 */
	public TelaConfirmaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 379, 352);
		contentPane.add(panel);
		
		JLabel lblConfirmaoDeConsulta = new JLabel("Confirma\u00E7\u00E3o de Consulta");
		lblConfirmaoDeConsulta.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblConfirmaoDeConsulta.setBounds(51, 11, 275, 50);
		panel.add(lblConfirmaoDeConsulta);
		
		JLabel label_1 = new JLabel("Consultas para confirma��o: ");
		label_1.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label_1.setBounds(23, 90, 307, 26);
		panel.add(label_1);
		
		JComboBox<String> comboBoxConsultas = new JComboBox<String>();
		comboBoxConsultas.setFont(new Font("Rockwell", Font.PLAIN, 22));
		comboBoxConsultas.setBounds(23, 138, 328, 32);
		panel.add(comboBoxConsultas);

		JLabel lblMdicos = new JLabel("M\u00E9dicos:");
		lblMdicos.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblMdicos.setBounds(23, 180, 167, 32);
		panel.add(lblMdicos);
		
		JComboBox<String> comboBoxMedicos = new JComboBox<String>();
		comboBoxMedicos.setFont(new Font("Rockwell", Font.PLAIN, 22));
		comboBoxMedicos.setBounds(23, 223, 327, 32);
		comboBoxMedicos.setEnabled(false);
		panel.add(comboBoxMedicos);
		List<Consulta> consultasParaConfirmacao = Fachada.listaConsultasParaConfirmacao();
		for (int i = 0; i < consultasParaConfirmacao.size(); i++)
			comboBoxConsultas.addItem(i+1 + " - " + consultasParaConfirmacao.get(i).getPaciente().getNome().split(" ")[0]+
					" " + consultasParaConfirmacao.get(i).getdataHorario().toString());
		comboBoxConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] dados = String.valueOf(comboBoxConsultas.getSelectedItem()).split(" ");
				consulta = consultasParaConfirmacao.get(Integer.parseInt(dados[0]) - 1);
				List<Medico> medicosSelecionados = Fachada.especialistasDisponiveisPorHorario(consulta.getdataHorario(), consulta.getEspecialidade().getDescricao());
				if (!medicosSelecionados.isEmpty()) {
					comboBoxMedicos.setEnabled(true);
					comboBoxMedicos.removeAllItems();
					for (Medico m : medicosSelecionados)
						comboBoxMedicos.addItem(m.getNome().split("0")[0] +" - "+ m.getCpf());
				} else {
					JOptionPane.showMessageDialog(contentPane, "Nao existem medicos disponiveis!", "Confirmacao de Consulta Cancelada",
							JOptionPane.ERROR_MESSAGE);
					TelaSecretario telaSecretario = new TelaSecretario();
					telaSecretario.setVisible(true);
					dispose();
				}
			}
		});
		
		JButton button = new JButton("Solicitar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] dados = String.valueOf(comboBoxMedicos.getSelectedItem()).split(" - ");
				try {
					Consulta c = Fachada.confirmaConsulta(consulta, dados[1]);
					if (c != null) {
						JOptionPane.showMessageDialog(contentPane, "Confirma��o conclu�da!", "Confirma��o de Consulta",
								JOptionPane.INFORMATION_MESSAGE);
						TelaSecretario telaSecretario = new TelaSecretario();
						telaSecretario.setVisible(true);
						dispose();
//						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Erro na confirma��o!", "Confirma��o de Consulta",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(111, 290, 145, 35);
		panel.add(button);
	}
}
