package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ManutencaoProdutoView extends JDialog {
	
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTable table;
	private JTextField tfCdBarras;
	private JComboBox cbStatus;
	private TableModel tableModel;
	
	private IServicoBeanLocal serviceBeanLocal;
	
	/**
	 * Create the dialog.
	 */
	
	public ManutencaoProdutoView() {
		
		serviceBeanLocal = new ServicoBeanLocal();
		
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 664, 124);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 22, 60, 14);
		panel.add(lblCodigo);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 58, 60, 14);
		panel.add(lblStatus);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(69, 19, 95, 20);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);

		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.insertItemAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(69, 54, 95, 22);
		panel.add(cbStatus);

		JLabel lblCdBarras = new JLabel("Cód. Barras");
		lblCdBarras.setBounds(200, 58, 74, 14);
		panel.add(lblCdBarras);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(200, 22, 60, 14);
		panel.add(lblDescricao);

		tfDescricao = new JTextField();
		tfDescricao.setBounds(284, 19, 370, 20);
		panel.add(tfDescricao);
		tfDescricao.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(505, 86, 89, 23);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(406, 86, 89, 23);
		panel.add(btnLimpar);

		tfCdBarras = new JTextField();
		tfCdBarras.setBounds(284, 56, 370, 20);
		panel.add(tfCdBarras);
		tfCdBarras.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 180, 664, 130);
		getContentPane().add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. Compra");
		tableModel.addColumn("Vlr. Venda");
		tableModel.addColumn("Cd. Barra");
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(70);
		tableColumnModel.getColumn(1).setPreferredWidth(110);
		tableColumnModel.getColumn(2).setPreferredWidth(90);
		tableColumnModel.getColumn(3).setPreferredWidth(60);
		tableColumnModel.getColumn(4).setPreferredWidth(80);
		tableColumnModel.getColumn(5).setPreferredWidth(80);
		tableColumnModel.getColumn(6).setPreferredWidth(160);
		
		pesquisar();

		scrollPane.setViewportView(table);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		
		
		btnAdicionar.setBounds(10, 146, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(106, 146, 89, 23);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(205, 146, 89, 23);
		getContentPane().add(btnExcluir);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(585, 327, 89, 23);
		getContentPane().add(btnFechar);

		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}
	
	private void editar() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, 
					"É necessário selecionar um registro!",
					"Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		}else {
			
			ProdutoView pv = 
					new ProdutoView(this);
			
			ProdutoVO aux = (ProdutoVO)tableModel.getRows().get(table.getSelectedRow())
					.getElement();
			
			pv.editar(aux);
			
			pv.setVisible(true);
			
		}
		
	}

	private void fechar() {
		this.setVisible(false);
		this.dispose();
	}
	
	protected void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {
			
			StatusEnum statusEnum = (StatusEnum) cbStatus.getSelectedItem();
					
			String statusProduto = null;
			if (statusEnum != null) {
				statusProduto = statusEnum.name();
			}
			
			String idProd = tfCodigo.getText();
			BigInteger idProdBI = null;
			if(idProd != null && idProd.trim().length()>0) {
				idProdBI = new BigInteger(idProd);
			}
			
			DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
			DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.000");
						
			
			List<ProdutoVO> produto = serviceBeanLocal.listarProduto(idProdBI, tfDescricao.getText(), statusProduto,
					tfCodigo.getText(), Dados.getClienteSelecionado());			
			
			for (ProdutoVO produtoVO : produto) {

				RowData rowData = new RowData();
				rowData.getValues().put(0, produtoVO.getId().toString());
				rowData.getValues().put(1, produtoVO.getDescri());
				rowData.getValues().put(2, produtoVO.getQtdest());
				rowData.getValues().put(3, produtoVO.getStatus());
				rowData.getValues().put(4, produtoVO.getValcom());
				rowData.getValues().put(5, produtoVO.getValven());
				rowData.getValues().put(6, produtoVO.getCodbar());
				rowData.setElement(produtoVO);
				tableModel.addRow(rowData);
			}
			
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void limpar() {

		tfCodigo.setText(null);
		tfDescricao.setText(null);
		tfCdBarras.setText(null);
		cbStatus.setSelectedIndex(0);
		pesquisar();
	}
	
	private void adicionar() {
		
		ProdutoView mpv = new ProdutoView(this);
		mpv.setVisible(true);
		
	}
	
	private void excluir() {
		
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso!",
					JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o registro?", "Confirmação",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				ProdutoVO produtoVO = (ProdutoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				try {
					serviceBeanLocal.excluirProduto(produtoVO);
					pesquisar();
				} catch (BOException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}

	}


