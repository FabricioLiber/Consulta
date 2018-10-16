package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
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

		lblUser = new JLabel("Usuário: ");
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
					System.out.println(String.valueOf(textFieldPassword.getPassword()));
					Usuario usuario = Fachada.realizarLogin(textFieldUser.getText(), String.valueOf(textFieldPassword.getPassword()));
					if (usuario != null) {
						System.out.println("entrou");
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
					}else {
						System.out.println("É nulo");
					}
				}
				catch(Exception erro){
	//				lblmsg.setText(erro.getMessage());
					erro.printStackTrace();
				}
				Fachada.finalizar();
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
				dispose();
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 22));
		button.setBounds(227, 11, 34, 34);
		contentPane.add(button);
		Fachada.finalizar();
	}

}
