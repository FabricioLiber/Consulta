package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JLabel lblTitulo;
	private JTable table;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMedico frame = new TelaMedico();
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
	public TelaMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 664, 129);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		lblTitulo = new JLabel("Login");
		lblTitulo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblTitulo.setBounds(108, 11, 66, 50);
		panelTitulo.add(lblTitulo);
		
		JRadioButton botaoAtendida = new JRadioButton("Consultas atendidas");
		botaoAtendida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Consulta> consultasAtendidas = Fachada.listaConsultasRealizadasPorUsuario(Fachada.getLogado());
				adicionaItemsTabela(consultasAtendidas);
			}
		});
		
		botaoAtendida.setBounds(48, 82, 137, 23);
		panelTitulo.add(botaoAtendida);
		
		JRadioButton botaoAtender = new JRadioButton("Consultas a atender");
		botaoAtender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Consulta> consultasAtendidas = Fachada.listaConsultasARealizarPorUsuario(Fachada.getLogado());
				adicionaItemsTabela(consultasAtendidas);
			}
		});
		
		botaoAtender.setBounds(210, 82, 150, 23);
		panelTitulo.add(botaoAtender);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoAtendida);
		buttonGroup.add(botaoAtender);
		
		JPanel panelConteudo = new JPanel();
		panelConteudo.setBounds(0, 130, 664, 350);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);
		
		table = new JTable(tableModel);
		table.setBounds(41, 42, 1, 1);
		table.setBounds(190, 383, 191, 250);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 93, 286, 208);
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
