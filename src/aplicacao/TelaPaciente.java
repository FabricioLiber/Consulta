package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Consulta;
import modelo.TableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class TelaPaciente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPaciente frame = new TelaPaciente();
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
	public TelaPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 664, 129);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Paciente");
		label.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label.setBounds(108, 11, 130, 50);
		panel.add(label);
		
		JRadioButton botaoRealizada = new JRadioButton("Consultas realizadas");
		botaoRealizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botaoRealizada.setBounds(48, 82, 150, 23);
		panel.add(botaoRealizada);
		
		JRadioButton botaoAgendada = new JRadioButton("Consultas agendadas");
		botaoAgendada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botaoAgendada.setBounds(238, 82, 150, 23);
		panel.add(botaoAgendada);
		
		JRadioButton botaoSolicitada = new JRadioButton("Consultas solicitadas");
		botaoSolicitada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botaoSolicitada.setBounds(420, 82, 150, 23);
		panel.add(botaoSolicitada);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoRealizada);
		buttonGroup.add(botaoAgendada);
		buttonGroup.add(botaoSolicitada);
		
		JButton btnSolicitarConsulta = new JButton("Solicitar Consulta");
		btnSolicitarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSolicitarConsulta.setBounds(487, 27, 150, 34);
		panel.add(btnSolicitarConsulta);
	

		table = new JTable(tableModel);
		table.setBounds(41, 42, 1, 1);
		table.setBounds(190, 383, 191, 250);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(27, 132, 614, 246);
		contentPane.add(scroll);
	}
	
	public void adicionaItemsTabela (List<Consulta> consultas) {
		Object [] dado = null;
		if (consultas == null) {
			System.out.println("Nenhuma conta cadastrada!");
		}
		else {
			dado = new Object[4];
			String [] colunas = {"Data", "Paciente", "Secretario", "Especialidade"};
			tableModel = new TableModel(consultas.size(), colunas);
			for (int i = 0; i < consultas.size(); i++) {
				dado[0] = consultas.get(i).getdataHorario().toString();
				dado[1] = consultas.get(i).getPaciente().getNome();
				dado[2] = consultas.get(i).getSecretario().getNome();
				dado[3] = consultas.get(i).getEspecialidade().getDescricao();
				tableModel.addRow(dado);
			}
//			label.setText("Listagem de contas:");
		}
	}
}
