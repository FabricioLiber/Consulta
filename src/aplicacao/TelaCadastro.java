package aplicacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import fachada.Fachada;
import modelo.Convenio;
import modelo.Endereco;
import modelo.Especialidade;
import modelo.Usuario;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha;
	private JTextField textFieldNomeCompleto;
	private JFormattedTextField formattedTextFieldCPF;
	private JFormattedTextField JFormattedTextFieldCep;
	private JTextField textFieldLogradouro;
	private JTextField textFieldNumero;
	private JTextField textFieldCidade;
	private JTextField textFieldEstado;
	private JTextField textFieldBairro;
	private JLabel lblCrm;
	private JLabel lblConvenio;
	private JComboBox<String> listaConvenios;
	private JTextField textFieldCrm;
	private JPasswordField passwordFieldConfirmeSenha;
	private JComboBox<String> listaEspecialidades;
	private JLabel lblEspecialidades;

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 547, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 591, 118);
		contentPane.add(panel);
		
		JLabel lblEmQualDesses = new JLabel("Em qual desses grupos vc est\u00E1 inserido?");
		lblEmQualDesses.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblEmQualDesses.setBounds(20, 52, 361, 39);
		panel.add(lblEmQualDesses);
		
		
		lblCrm = new JLabel("CRM");
		lblCrm.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblCrm.setBounds(239, 100, 96, 26);
		lblCrm.setVisible(false);
		panel_1.add(lblCrm);
		
		textFieldCrm = new JTextField();
		textFieldCrm.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldCrm.setColumns(10);
		textFieldCrm.setBounds(239, 134, 110, 29);
		textFieldCrm.setVisible(false);
		panel_1.add(textFieldCrm);
		

		lblConvenio = new JLabel("Conv�nio");
		lblConvenio.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblConvenio.setBounds(369, 100, 137, 26);
		lblConvenio.setVisible(false);
		panel_1.add(lblConvenio);
		
		listaConvenios = new JComboBox<String>();
		listaConvenios.setFont(new Font("Rockwell", Font.PLAIN, 18));
		listaConvenios.setBounds(369, 129, 137, 32);
		panel_1.add(listaConvenios);

		listaConvenios.removeAllItems();
		for (Convenio c : Fachada.listaDeConvenios())
			listaConvenios.addItem(c.getDescricao());
		listaConvenios.setVisible(false);
		
		lblEspecialidades = new JLabel("Especialidade");
		lblEspecialidades.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblEspecialidades.setBounds(369, 100, 137, 26);
		lblEspecialidades.setVisible(false);
		panel_1.add(lblEspecialidades);
		
		
		JRadioButton botaoPaciente = new JRadioButton("Paciente");
		botaoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCrm.setVisible(false);
				textFieldCrm.setVisible(false);
				
				lblConvenio.setVisible(true);
				listaConvenios.setVisible(true);
				
				lblEspecialidades.setVisible(false);
				listaEspecialidades.setVisible(false);
			}
		});
		botaoPaciente.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoPaciente.setBounds(20, 88, 95, 23);
		panel.add(botaoPaciente);
		
		JRadioButton botaoMedico = new JRadioButton("M�dico");
		botaoMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblCrm.setVisible(true);
				textFieldCrm.setVisible(true);
				
				lblConvenio.setVisible(false);
				listaConvenios.setVisible(false);
				
				lblEspecialidades.setVisible(true);
				listaEspecialidades.setVisible(true);
			}
		});
		
		botaoMedico.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoMedico.setBounds(149, 88, 88, 23);
		panel.add(botaoMedico);
		
		JRadioButton botaoSecretario = new JRadioButton("Secret\u00E1rio");
		botaoSecretario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCrm.setVisible(false);
				textFieldCrm.setVisible(false);
				
				lblConvenio.setVisible(false);
				listaConvenios.setVisible(false);
				
				lblEspecialidades.setVisible(false);
				listaEspecialidades.setVisible(false);
			}
		});
		botaoSecretario.setFont(new Font("Rockwell", Font.PLAIN, 16));
		botaoSecretario.setBounds(275, 88, 106, 23);
		panel.add(botaoSecretario);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(botaoPaciente);
		buttonGroup.add(botaoMedico);
		buttonGroup.add(botaoSecretario);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblCadastro.setBounds(221, 0, 95, 49);
		panel.add(lblCadastro);
		panel_1.setBounds(0, 120, 531, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblUsurio.setBounds(369, 205, 96, 26);
		panel_1.add(lblUsurio);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(369, 242, 110, 29);
		panel_1.add(textFieldUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblSenha.setBounds(369, 275, 86, 32);
		panel_1.add(lblSenha);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setFont(new Font("Rockwell", Font.PLAIN, 16));
		passwordFieldSenha.setBounds(369, 318, 110, 32);
		panel_1.add(passwordFieldSenha);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo");
		lblNomeCompleto.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblNomeCompleto.setBounds(23, 30, 137, 26);
		panel_1.add(lblNomeCompleto);
		
		textFieldNomeCompleto = new JTextField();
		textFieldNomeCompleto.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldNomeCompleto.setColumns(10);
		textFieldNomeCompleto.setBounds(23, 65, 295, 29);
		panel_1.add(textFieldNomeCompleto);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblCpf.setBounds(369, 30, 49, 26);
		panel_1.add(lblCpf);
		
		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			formattedTextFieldCPF = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCPF.setBounds(369, 65, 110, 29);
			panel_1.add(formattedTextFieldCPF);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblDataDeNascimento.setBounds(23, 97, 172, 32);
		panel_1.add(lblDataDeNascimento);
		DatePicker data;
		
		data = new DatePicker();
		data.setFont(new Font("Rockwell", Font.PLAIN, 14));
		data.setBounds(23, 135, 200, 32);
		panel_1.add(data);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblLogradouro.setBounds(23, 212, 96, 26);
		panel_1.add(lblLogradouro);
		
		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldLogradouro.setColumns(10);
		textFieldLogradouro.setBounds(23, 249, 236, 29);
		panel_1.add(textFieldLogradouro);
		
		JLabel lblNumero = new JLabel("N\u00BA");
		lblNumero.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblNumero.setBounds(268, 212, 30, 26);
		panel_1.add(lblNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(269, 249, 43, 29);
		panel_1.add(textFieldNumero);
		
		JLabel lblBairro = new JLabel("CEP");
		lblBairro.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblBairro.setBounds(23, 281, 55, 26);
		panel_1.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblCidade.setBounds(23, 371, 96, 26);
		panel_1.add(lblCidade);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldCidade.setColumns(10);
		textFieldCidade.setBounds(23, 408, 110, 29);
		panel_1.add(textFieldCidade);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldEstado.setColumns(10);
		textFieldEstado.setBounds(202, 408, 110, 29);
		panel_1.add(textFieldEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblEstado.setBounds(202, 371, 96, 26);
		panel_1.add(lblEstado);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setFont(new Font("Rockwell", Font.PLAIN, 16));
		textFieldBairro.setColumns(10);
		textFieldBairro.setBounds(202, 321, 110, 29);
		panel_1.add(textFieldBairro);
		
		JLabel label_2 = new JLabel("Bairro");
		label_2.setFont(new Font("Rockwell", Font.PLAIN, 16));
		label_2.setBounds(202, 282, 55, 26);
		panel_1.add(label_2);
		
		MaskFormatter mascaraCep = null;
		try {
			mascaraCep = new MaskFormatter("#####-###");
			
			JFormattedTextFieldCep = new JFormattedTextField(mascaraCep);
			JFormattedTextFieldCep.setBounds(23, 321, 110, 29);
			panel_1.add(JFormattedTextFieldCep);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblInformaesPessoais = new JLabel("Informa\u00E7\u00F5es pessoais");
		lblInformaesPessoais.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblInformaesPessoais.setBounds(23, 0, 200, 32);
		panel_1.add(lblInformaesPessoais);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblEndereo.setBounds(23, 169, 200, 32);
		panel_1.add(lblEndereo);
		
		JLabel lblInformaesDeAcesso = new JLabel("Informa\u00E7\u00F5es de Acesso");
		lblInformaesDeAcesso.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblInformaesDeAcesso.setBounds(331, 169, 200, 32);
		panel_1.add(lblInformaesDeAcesso);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Endereco endereco = new Endereco(textFieldLogradouro.getText(), Integer.parseInt(textFieldNumero.getText()),
						String.valueOf(JFormattedTextFieldCep.getValue()), textFieldBairro.getText(), textFieldCidade.getText(),
						textFieldEstado.getText());
				String senha = (String.valueOf(passwordFieldSenha.getPassword()));
				Usuario usuario = null;
				if(senha.equals(String.valueOf(passwordFieldSenha.getPassword()))) {
					if (botaoPaciente.isSelected()) {						
						try {
							usuario = Fachada.cadastrarUsuario(textFieldUsuario.getText(), senha, textFieldNomeCompleto.getText(),
									formattedTextFieldCPF.getText(), data.getDate(), endereco,
									String.valueOf(listaConvenios.getSelectedItem()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (botaoMedico.isSelected()) {
						try {
							usuario = Fachada.cadastrarUsuario(textFieldUsuario.getText(), senha, textFieldNomeCompleto.getText(),
									formattedTextFieldCPF.getText(), data.getDate(), endereco,
									textFieldCrm.getText(), String.valueOf(listaEspecialidades.getSelectedItem()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (botaoSecretario.isSelected()) {
						try {
							usuario = Fachada.cadastrarUsuario(textFieldUsuario.getText(), senha, textFieldNomeCompleto.getText(),
									formattedTextFieldCPF.getText(), data.getDate(), endereco);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "� necess�rio escolher um grupo!", "Cadastro", JOptionPane.DEFAULT_OPTION);
					}
					if (usuario == null)
						JOptionPane.showMessageDialog(contentPane, "Falha ao cadastrar o cliente!", "Cadastro", JOptionPane.ERROR_MESSAGE);
					else {
						System.out.println(usuario);
						JOptionPane.showMessageDialog(contentPane, "Cadastro conclu�do!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(true);
//						setVisible(false);
						dispose();
						}
				} else
					JOptionPane.showMessageDialog(contentPane, "Erro no cadastro da senha, coloque a senha e confirme novamente!", "Senha", JOptionPane.INFORMATION_MESSAGE);
			}		
		});
		btnCadastrar.setFont(new Font("Rockwell", Font.PLAIN, 22));
		btnCadastrar.setBounds(193, 474, 156, 35);
		panel_1.add(btnCadastrar);
		
		JLabel lblConfirmeSenha = new JLabel("Confirme sua senha");
		lblConfirmeSenha.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblConfirmeSenha.setBounds(369, 362, 152, 32);
		panel_1.add(lblConfirmeSenha);
		
		passwordFieldConfirmeSenha = new JPasswordField();
		passwordFieldConfirmeSenha.setFont(new Font("Rockwell", Font.PLAIN, 16));
		passwordFieldConfirmeSenha.setBounds(369, 405, 110, 32);
		panel_1.add(passwordFieldConfirmeSenha);
		
		listaEspecialidades = new JComboBox<String>();
		listaEspecialidades.setFont(new Font("Rockwell", Font.PLAIN, 18));
		listaEspecialidades.setBounds(369, 131, 137, 32);
		panel_1.add(listaEspecialidades);

		listaEspecialidades.removeAllItems();
		for (Especialidade e : Fachada.listaDeEspecialidades())
			listaEspecialidades.addItem(e.getDescricao());
		listaEspecialidades.setVisible(false);
	}
}
