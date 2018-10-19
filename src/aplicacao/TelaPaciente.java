package aplicacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
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

public class TelaPaciente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JScrollPane scroll;
	private JLabel labelInfo;
	private JPanel panelConteudo;

	/**
	 * Create the frame.
	 */
	public TelaPaciente() {
		Fachada.inicializar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 670, 117);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Verifique suas consultas, sr(a). " + Fachada.getNomeUsuarioLogado());
		label.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label.setBounds(20, 11, 578, 50);
		panel.add(label);
		
		JRadioButton botaoRealizada = new JRadioButton("Consultas realizadas");
		botaoRealizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Fachada.inicializar();
				List<Consulta> consultasAtendidas = Fachada.listaConsultasRealizadasPorUsuario();
				adicionaItemsTabela(consultasAtendidas);
			}
		});
		botaoRealizada.setBounds(20, 82, 185, 23);
		botaoRealizada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		panel.add(botaoRealizada);
		
		JRadioButton botaoAgendada = new JRadioButton("Consultas agendadas");
		botaoAgendada.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {

				Fachada.inicializar();
				List<Consulta> consultasAtender = Fachada.listaConsultasARealizarPorUsuario();
				adicionaItemsTabela(consultasAtender);
				Fachada.finalizar();
			}
		});
		botaoAgendada.setBounds(235, 82, 185, 23);
		botaoAgendada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		panel.add(botaoAgendada);
		
		JRadioButton botaoSolicitada = new JRadioButton("Consultas solicitadas");
		botaoSolicitada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.inicializar();
				List<Consulta> consultasSolicitadas = Fachada.listarConsultasSolicitadasPorPaciente();
				Object [] dado = null;
				if (consultasSolicitadas.isEmpty()) {
					labelInfo.setText("Nenhuma consulta cadastrada!");
				}
				else {
					labelInfo.setText("");
					dado = new Object[2];
					String [] colunas = {"Data", "Especialidade"};
					tableModel = new TableModel(consultasSolicitadas.size(), colunas);
					for (int i = 0; i < consultasSolicitadas.size(); i++) {
						dado[0] = consultasSolicitadas.get(i).getdataHorario().toString();
						dado[1] = consultasSolicitadas.get(i).getEspecialidade().getDescricao();
						tableModel.addRow(dado);
					}
					table = new JTable(tableModel);
					scroll = new JScrollPane(table);
					scroll.setBounds(25, 10, 610, 150);
					panelConteudo.add(scroll);
				}
				Fachada.finalizar();
			}
		});
		botaoSolicitada.setBounds(461, 82, 185, 23);
		botaoSolicitada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		panel.add(botaoSolicitada);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoRealizada);
		buttonGroup.add(botaoAgendada);
		buttonGroup.add(botaoSolicitada);
		

		ImageIcon img = new ImageIcon("src/img/sair.png");
		JButton botaoSair = new JButton(img);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(contentPane, "Deseja fazer LogOff?", "LogOff",
						JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION) {
					try {
						Fachada.realizarLogoff();
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(true);
						dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		});
		botaoSair.setFont(new Font("Rockwell", Font.PLAIN, 22));
		botaoSair.setBounds(612, 11, 34, 34);
		panel.add(botaoSair);
		
		panelConteudo = new JPanel();
		panelConteudo.setBounds(0, 116, 670, 273);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);
		
		JButton button = new JButton("Solicitar Consulta");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSocilitaConsulta tela = new TelaSocilitaConsulta();
				tela.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(398, 215, 243, 35);
		panelConteudo.add(button);
		
		labelInfo = new JLabel("");
		labelInfo.setBounds(24, 215, 367, 35);
		labelInfo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		panelConteudo.add(labelInfo);

		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(25, 10, 610, 150);
		panelConteudo.add(scroll);
		scroll.setVisible(false);
		Fachada.finalizar();
	}
	
	public void adicionaItemsTabela (List<Consulta> consultas) {
		Object [] dado = null;
		if (consultas.isEmpty()) {
			labelInfo.setText("Nenhuma consulta cadastrada!");
			if (scroll != null)
				scroll.setVisible(false);
		} else {
			labelInfo.setText("");
			dado = new Object[4];
			String [] colunas = {"Data", "Medico", "Secretario", "Especialidade"};
			tableModel = new TableModel(consultas.size(), colunas);
			for (int i = 0; i < consultas.size(); i++) {
				dado[0] = consultas.get(i).getdataHorario().toString();
				dado[1] = (consultas.get(i).getMedico() != null ? consultas.get(i).getMedico().getNome() : "Indisponível");
				dado[2] = (consultas.get(i).getSecretario() != null ? consultas.get(i).getSecretario().getNome() : "Indisponível");
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
}
