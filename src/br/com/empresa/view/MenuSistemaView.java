package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class MenuSistemaView extends JFrame {

	/**
	 * Create the frame.
	 */
	public MenuSistemaView() {
		setTitle("Sistema simples de venda");
		setBounds(100, 100, 652, 429);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mniSair = new JMenuItem("Sair");
		mniSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		mnArquivo.add(mniSair);

		JMenu mnManutencao = new JMenu("Manutenção");
		menuBar.add(mnManutencao);

		JMenuItem mniConsumidor = new JMenuItem("Consumidor/Fornecedor");
		mniConsumidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manterConsumidorFornecedor();
			}
		});
		mnManutencao.add(mniConsumidor);
		
		JMenuItem mniProduto = new JMenuItem("Manutenção de Produto");
		mniProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manterProduto();
			}
		});
		mnManutencao.add(mniProduto);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mniSobre = new JMenuItem("Sobre");
		mniSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sobre();
			}
		});
		mnAjuda.add(mniSobre);

		setExtendedState(Frame.MAXIMIZED_BOTH);

		try {

			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");
			BufferedImage img = ImageIO.read(streamLogo);

			ImageIcon imageIcon = new ImageIcon(img);
			JLabel centerLabel = new JLabel(imageIcon);

			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);

			getContentPane().add(main, BorderLayout.CENTER);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir tela.", 
					"Erro!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void manterConsumidorFornecedor() {
		
		JDialog consultaConsumidorFornecedorView = new ConsultaConsumidorFornecedorView();
		consultaConsumidorFornecedorView.setModal(true);
		consultaConsumidorFornecedorView.setVisible(true);
		
	}
	private void manterProduto(){
		JDialog manutencaoProdutoView = new ManutencaoProdutoView();
		manutencaoProdutoView.setModal(true);
		manutencaoProdutoView.setVisible(true);
	}

	private void sair() {

		Object[] options = { "Sim", "Não" };
		int n = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Confirmação",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (n == 0) {
			System.exit(0);
		}

	}

	private void sobre() {
		JOptionPane.showMessageDialog(null, "TOP, meu primeiro programa profi.");
	}

}
