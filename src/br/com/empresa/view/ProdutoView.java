package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ProdutoView extends JDialog {

	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCdBarras;
	private JTextField tfQtdEstoque;
	private JTextField tfVlrCompra;
	private JTextField tfVlrVenda;
	private JComboBox cbStatus;
	
	private ProdutoVO produtoVO;

	private IServicoBeanLocal servicoBeanLocal;

	private ManutencaoProdutoView telaAnterior;

	public ProdutoView(ManutencaoProdutoView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
		telaAnterior = jDialog;
	}

	public ProdutoView() {
		inicializarComponentes();

	}

	private void inicializarComponentes() {

		servicoBeanLocal = new ServicoBeanLocal();
		produtoVO = new ProdutoVO();
		
		setBounds(100, 100, 450, 330);
		getContentPane().setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 20, 76, 14);
		getContentPane().add(lblCodigo);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 54, 76, 14);
		getContentPane().add(lblDescricao);

		JLabel lblCdBarras = new JLabel("Cód. Barras:");
		lblCdBarras.setBounds(10, 88, 80, 14);
		getContentPane().add(lblCdBarras);

		JLabel lblQtdEstoque = new JLabel("Qtd. Estoque:");
		lblQtdEstoque.setBounds(10, 122, 76, 14);
		getContentPane().add(lblQtdEstoque);

		JLabel lblVlrCompra = new JLabel("Vlr. Compra:");
		lblVlrCompra.setBounds(10, 156, 76, 14);
		getContentPane().add(lblVlrCompra);

		JLabel lblVlrVenda = new JLabel("Vlr. Venda:");
		lblVlrVenda.setBounds(10, 190, 76, 14);
		getContentPane().add(lblVlrVenda);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 224, 76, 14);
		getContentPane().add(lblStatus);

		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setBounds(96, 17, 110, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		tfDescricao = new JTextField();
		tfDescricao.setBounds(96, 51, 328, 20);
		getContentPane().add(tfDescricao);
		tfDescricao.setColumns(10);

		tfCdBarras = new JTextField();
		tfCdBarras.setColumns(10);
		tfCdBarras.setBounds(96, 85, 158, 20);
		getContentPane().add(tfCdBarras);

		tfQtdEstoque = new JTextField();
		tfQtdEstoque.setColumns(10);
		tfQtdEstoque.setBounds(96, 119, 86, 20);
		getContentPane().add(tfQtdEstoque);

		tfVlrCompra = new JTextField();
		tfVlrCompra.setColumns(10);
		tfVlrCompra.setBounds(96, 153, 110, 20);
		getContentPane().add(tfVlrCompra);

		tfVlrVenda = new JTextField();
		tfVlrVenda.setColumns(10);
		tfVlrVenda.setBounds(96, 187, 110, 20);
		getContentPane().add(tfVlrVenda);

		cbStatus = new JComboBox();
		cbStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarStatus();
			}
		});

		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.setBounds(96, 220, 110, 22);
		getContentPane().add(cbStatus);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(335, 257, 89, 23);
		getContentPane().add(btnFechar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(236, 257, 89, 23);
		getContentPane().add(btnSalvar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 249, 414, 2);
		getContentPane().add(separator);

		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			

	}

	private void fechar() {
		this.setVisible(false);
		this.dispose();
	}

	private void salvar() {
		
		
		
		StatusEnum se = (StatusEnum) cbStatus.getSelectedItem();
		produtoVO.setStatus(se.name()); 
		produtoVO.setDescri(tfDescricao.getText());
		produtoVO.setCodbar(tfCdBarras.getText());
		produtoVO.setQtdest(tfQtdEstoque.getText());
		produtoVO.setValcom(vlrCompra.getText());
		produtoVO.setValven(tfVlrVenda.getText());
		
				
		produtoVO.setClient(Dados.getClienteSelecionado());

		StatusEnum statusEnum = (StatusEnum) cbStatus.getSelectedItem();

		try {

			servicoBeanLocal.salvarProduto(produtoVO);

			tfCodigo.setText(produtoVO.getId().toString());

			telaAnterior.pesquisar();

			JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!", "Mensagem de confirmação",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação", "Mensagem de erro",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

	}

	public void editar(ProdutoVO produto) {

		this.produtoVO = produto;
		this.tfDescricao.setText(produto.getId().toString());
		this.cbStatus.setSelectedItem(TipoPessoaEnum.valueOf(produto.getStatus()));
		this.tfCdBarras.setText(produto.getCodbar());
		this.tfQtdEstoque.setText(produto.getDescri());

	}
	
	private void alterarStatus() {
		StatusEnum statusEnum = (StatusEnum) cbStatus.getSelectedItem();

	}

}
