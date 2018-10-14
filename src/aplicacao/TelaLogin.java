package aplicacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TelaLogin {
	
	private JFrame frame;
	private JLabel lblTitulo;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JPasswordField textFieldPassword;
	/**
	 * Launch the application.
	 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaLogin window = new TelaLogin();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	/**
	 * Create the frame.
	 */
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Consulta Medica");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 291, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTitulo = new JLabel("Login");
		lblTitulo.setFont(new Font("Rockwell", Font.PLAIN, 22));
		lblTitulo.setBounds(108, 11, 66, 50);
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
					Usuario usuario = Fachada.verificaUsuario(textFieldUser.getText(), String.valueOf(textFieldPassword.getPassword()));
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
						frame.dispose();
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
		
	}
	public TelaLogin () {
		initialize();
		
	}
}
