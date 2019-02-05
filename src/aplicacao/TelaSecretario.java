package aplicacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Consulta;
import modelo.TableModel;

public class TelaSecretario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JLabel lblInfo;
	private JScrollPane scroll;

	/**
	 * Create the frame.
	 */
	public TelaSecretario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelConteudo = new JPanel();
		panelConteudo.setBounds(0, 118, 670, 239);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);
		
		JButton button = new JButton("Confirmar Consulta");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConfirmaConsulta telaConfirmaConsulta = new TelaConfirmaConsulta();
				telaConfirmaConsulta.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(417, 367, 243, 35);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 676, 117);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Verifique suas consultas, Funcion�rio(a) "+ Fachada.getNomeUsuarioLogado());
		label.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label.setBounds(20, 11, 578, 50);
		panel.add(label);
		
		JRadioButton botaoConsultaAgendada = new JRadioButton("Consultas agendadas");
		botaoConsultaAgendada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Consulta> consultas = Fachada.listaConsultasARealizarPorUsuario();
				
				Object [] dado = null;
				if (consultas.isEmpty()) {
					lblInfo.setText("Nenhuma consulta cadastrada!");
					if (scroll != null)
						scroll.setVisible(false);
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
					table = new JTable(tableModel);
					scroll = new JScrollPane(table);
					scroll.setBounds(25, 10, 610, 150);
					panelConteudo.add(scroll);
					scroll.setVisible(true);
				}
			}
		});
		botaoConsultaAgendada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoConsultaAgendada.setBounds(101, 82, 185, 23);
		panel.add(botaoConsultaAgendada);
		
		JRadioButton botaoConsultasAAgendar = new JRadioButton("Consultas para agendar");
		botaoConsultasAAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Consulta> consultas = Fachada.listaConsultasParaConfirmacao();
				Object [] dado = null;
				if (consultas.isEmpty()) {
					lblInfo.setText("Nenhuma consulta cadastrada!");
					if (scroll != null)
						scroll.setVisible(false);
				}
				else {
					dado = new Object[4];
					String [] colunas = {"Data", "Paciente", "CPF Paciente", "Especialidade"};
					tableModel = new TableModel(consultas.size(), colunas);
					for (int i = 0; i < consultas.size(); i++) {
						dado[0] = consultas.get(i).getdataHorario().toString();
						dado[1] = consultas.get(i).getPaciente().getNome();
						dado[2] = consultas.get(i).getPaciente().getCpf();
						dado[3] = consultas.get(i).getEspecialidade().getDescricao();
						tableModel.addRow(dado);
					}

					table = new JTable(tableModel);
					scroll = new JScrollPane(table);
					scroll.setBounds(25, 10, 610, 150);
					panelConteudo.add(scroll);
					scroll.setVisible(true);
				}
				
			}
		});
		botaoConsultasAAgendar.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoConsultasAAgendar.setBounds(370, 82, 213, 23);
		panel.add(botaoConsultasAAgendar);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoConsultasAAgendar);
		buttonGroup.add(botaoConsultaAgendada);		
		
		ImageIcon img = new ImageIcon("src/img/sair.png");
		JButton button_1 = new JButton(img);
		button_1.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button_1.setBounds(612, 11, 34, 34);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(contentPane, "Deseja fazer LogOff?", "LogOff",
						JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION) {
					try {
						Fachada.realizarLogoff();
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(true);
//						setVisible(false);
						dispose();
					} catch (Exception excecao) {
						// TODO Auto-generated catch block
						excecao.printStackTrace();
					}
				}
			}
		});
		
		lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblInfo.setBounds(10, 367, 367, 35);
		contentPane.add(lblInfo);
		
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(25, 10, 610, 150);
		panelConteudo.add(scroll);
		scroll.setVisible(false);
	}

	
}
