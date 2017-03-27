package br.org.energia.contadorhoras.ContadorHoras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame{

	private static final long serialVersionUID = -3865277920285281400L;
	public static Usuario user = new Usuario();
	static JFrame frame;
	
	public static Usuario montarTelaLogin(){
		frame = new JFrame("Login - Contador de Horas");
		frame.setSize(300, 150);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
		
		return user;
	}
	
	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("Entrar");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		
		JButton clearButton = new JButton("Limpar");
		clearButton.setBounds(180, 80, 80, 25);
		panel.add(clearButton);
		
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String usuario = new String(userText.getText());
				String senha = new String(passwordText.getPassword());
				 user.setUsuario(usuario);
				 user.setSenha(senha);
				 frame.dispose();
			}
		});
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passwordText.setText("");
				userText.requestFocus();
			}
		});
	}

}
