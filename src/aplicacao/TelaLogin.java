package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ConvenioDAO;
import dao.EspecialidadeDAO;
import fachada.Fachada;
import modelo.Convenio;
import modelo.Especialidade;
import modelo.Medico;
import modelo.Paciente;
import modelo.Secretario;
import modelo.Usuario;

public class TelaLogin extends JFrame {

	private JLabel lblTitulo;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JPasswordField textFieldPassword;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		Fachada.inicializar();		

		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade e = new Especialidade("Cancerologia", 170);
		especialidadeDAO.create(e);
		e = new Especialidade("Cardiologia", 120);
		especialidadeDAO.create(e);
		e = new Especialidade("dermatologia", 100);
		especialidadeDAO.create(e);
		e = new Especialidade("Ginecologia", 130);
		especialidadeDAO.create(e);
		e = new Especialidade("Neurologia", 160);
		especialidadeDAO.create(e);
		
		// Cria��o de Convenios
		ConvenioDAO convenioDAO = new ConvenioDAO();
		Convenio c = new Convenio("Unimed", 0.3);
		convenioDAO.create(c);
		c = new Convenio("Hapvida", 0.4);
		convenioDAO.create(c);
		c = new Convenio("Amil", 0.5);
		convenioDAO.create(c);
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Consulta Medica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTitulo = new JLabel("Login");
		lblTitulo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblTitulo.setBounds(105, 11, 66, 50);
		// TODO
		//lblTitulo.setFont(new Font());
		contentPane.add(this.lblTitulo);
		
		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Rockwell", Font.PLAIN, 22));
		textFieldUser.setBounds(116, 86, 145, 32);
		contentPane.add(this.textFieldUser);
		textFieldUser.setColumns(10);

		lblUser = new JLabel("Usu�rio: ");
		lblUser.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblUser.setBounds(10, 89, 96, 26);
		contentPane.add(this.lblUser);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(116, 143, 145, 32);
		textFieldPassword.setFont(new Font("Rockwell", Font.PLAIN, 22));
		contentPane.add(textFieldPassword);
		
		lblPassword = new JLabel("Senha: ");
		lblPassword.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblPassword.setBounds(10, 143, 86, 32);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Rockwell", Font.PLAIN, 22));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				Fachada.inicializar();
				try{
					Usuario usuario = Fachada.realizarLogin(textFieldUser.getText(), String.valueOf(textFieldPassword.getPassword()));
					if (usuario != null) {
						if (usuario instanceof Paciente){
							TelaPaciente telaPaciente = new TelaPaciente();
							telaPaciente.setVisible(true);
						} else if (usuario instanceof Secretario){
							TelaSecretario telaSecretario = new TelaSecretario();
							telaSecretario.setVisible(true);
						} else if (usuario instanceof Medico){
							TelaMedico telaMedico = new TelaMedico();
							telaMedico.setVisible(true);
						}
						dispose();
//						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(contentPane, "Usu�rio n�o encontrado!");
					}
				}
				catch(Exception erro){
	//				lblmsg.setText(erro.getMessage());
					erro.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(151, 218, 110, 35);
		contentPane.add(btnLogin);
		ImageIcon img = new ImageIcon("src/img/cadastrar.png");
		button = new JButton(img);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
//				setVisible(false);
				dispose();
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(227, 11, 34, 34);
		contentPane.add(button);
	}

}
