package br.com.empresa.view;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioClienteVO;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class SelecaoClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField tfFiltro;
	private JScrollPane scrollPane;
	private JList list;

	/**
	 * Create the frame.
	 */
	public SelecaoClienteView() {
		setTitle("Seleção de instituição");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(10, 11, 46, 14);
		contentPane.add(lblFiltro);

		tfFiltro = new JTextField();
		tfFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarValoresListModel();
			}
		});
		tfFiltro.setBounds(10, 27, 293, 20);
		contentPane.add(tfFiltro);
		tfFiltro.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 293, 193);
		contentPane.add(scrollPane);

		list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(list);

		ListModel<ClienteVO> listModel = new DefaultListModel<ClienteVO>();
		list.setModel(listModel);

		carregarValoresListModel();

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCliente();
			}
		});
		btnSelecionar.setBounds(10, 256, 100, 23);
		contentPane.add(btnSelecionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setBounds(214, 256, 89, 23);
		contentPane.add(btnCancelar);

		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, 
				dim.height / 2 - this.getSize().height / 2);

	}

	private void selecionarCliente() {

		DefaultListModel defaultListModel = (DefaultListModel) list.getModel();

		if (list.getSelectedIndex() >= 0) {

			ClienteVO clienteVO = (ClienteVO) defaultListModel.get(list.getSelectedIndex());
			Dados.setClienteSelecionado(clienteVO);

			MenuSistemaView menu = new MenuSistemaView();
			menu.setVisible(true);
			this.setVisible(false);
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente " + "para continuar.", "Validação",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void cancelar() {
		LoginView frame = new LoginView();
		frame.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	private void carregarValoresListModel() {

		IServicoBeanLocal servicoBeanLocal = new ServicoBeanLocal();
		try {

			List<UsuarioClienteVO> usuarioClientesVOs = servicoBeanLocal
					.listarClientesUsuario(Dados.getUsuarioLogado());

			DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
			defaultListModel.clear();

			if (tfFiltro != null && tfFiltro.getText() != null) {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClientesVOs) {

					if (usuarioClienteVO.getClienteVO().getDescri().contains(tfFiltro.getText())) {

						defaultListModel.addElement(usuarioClienteVO.getClienteVO());

					}
				}
			} else {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClientesVOs) {
					defaultListModel.addElement(usuarioClienteVO.getClienteVO());
				}
			}

		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
