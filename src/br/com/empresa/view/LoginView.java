package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.UsuarioVO;
import javax.swing.JPasswordField;


public class LoginView extends JFrame {
	
	private JTextField tfLogin;
	
	private IServicoBeanLocal servicoBeanLocal;	
	private JPasswordField pfSenha;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		
		setTitle("Autenticação do sistema");
		setBounds(100, 100, 314, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}
		});
		btnEntrar.setBounds(37, 81, 89, 23);
		getContentPane().add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(173, 81, 89, 23);
		getContentPane().add(btnSair);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 11, 46, 14);
		getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setBounds(10, 46, 46, 14);
		getContentPane().add(lblNewLabel);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(51, 8, 237, 20);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(51, 43, 237, 20);
		getContentPane().add(pfSenha);
		
		//Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, 
				dim.height / 2 - this.getSize().height / 2);
		
		

	}
	
	private void sair() {
		
		System.exit(0);
		
	}
	
	private void autenticarAcesso() {
		
		String login = this.tfLogin.getText();
		String senha = new String(this.pfSenha.getPassword());		
		try {
			
			UsuarioVO usuario = servicoBeanLocal.validarAcesso(login, senha);
			
			Dados.setUsuarioLogado(usuario);
			
			SelecaoClienteView selecao = new SelecaoClienteView();
			selecao.setVisible(true);
			
			super.setVisible(false);
			super.dispose();
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Mensagem de alerta!", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, procure "
					+ "o administrador", "Mensagem de erro!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
