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

public class TelaMedico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JScrollPane scroll;
	private JPanel panelConteudo;
	private JLabel lblInfo;

	/**
	 * Create the frame.
	 */
	public TelaMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelConteudo = new JPanel();
		panelConteudo.setBounds(0, 120, 676, 276);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 676, 117);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Verifique suas consultas, dr(a). "+ Fachada.getNomeUsuarioLogado());
		label.setFont(new Font("Rockwell", Font.PLAIN, 22));
		label.setBounds(20, 11, 578, 50);
		panel.add(label);
		
		JRadioButton botaoRealizada = new JRadioButton("Consultas realizadas");
		botaoRealizada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoRealizada.setBounds(115, 82, 185, 23);
		panel.add(botaoRealizada);
		botaoRealizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Consulta> consultasAtendidas = Fachada.listaConsultasRealizadasPorUsuario();
				adicionaItemsTabela(consultasAtendidas);
			}
		});
		
		JRadioButton botaoAgendada = new JRadioButton("Consultas agendadas");
		botaoAgendada.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoAgendada.setBounds(385, 82, 185, 23);
		panel.add(botaoAgendada);
		botaoAgendada.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				List<Consulta> consultasAtender = Fachada.listaConsultasARealizarPorUsuario();
				adicionaItemsTabela(consultasAtender);
			}
		});
		ImageIcon img = new ImageIcon("src/img/sair.png");
		JButton button = new JButton(img);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(contentPane, "Deseja fazer LogOff?", "LogOff",
						JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION) {
					try {
						Fachada.realizarLogoff();
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(true);
						dispose();
//						setVisible(false);
					} catch (Exception excecao) {
						// TODO Auto-generated catch block
						excecao.printStackTrace();
					}
				}
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(612, 11, 34, 34);
		panel.add(button);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoRealizada);
		buttonGroup.add(botaoAgendada);
		
		lblInfo = new JLabel("");
		lblInfo.setBounds(10, 396, 367, 35);
		contentPane.add(lblInfo);
		lblInfo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(25, 10, 610, 150);
		panelConteudo.add(scroll);
		scroll.setVisible(false);
	}
	
	public void adicionaItemsTabela (List<Consulta> consultas) {
		Object [] dado = null;
		if (consultas.isEmpty()) {
			lblInfo.setText("Nenhuma consulta cadastrada!");
			if (scroll != null)
				scroll.setVisible(false);
		}
		else {
			lblInfo.setText("");
			dado = new Object[4];
			String [] colunas = {"Data", "Paciente", "Secretario", "Especialidade"};
			tableModel = new TableModel(consultas.size(), colunas);
			for (int i = 0; i < consultas.size(); i++) {
				System.out.println(consultas.get(i));
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
}
