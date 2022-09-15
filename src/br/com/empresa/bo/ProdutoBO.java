package br.com.empresa.bo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO {

	private ProdutoDAO produtoDAO;

	public ProdutoBO() {
		produtoDAO = new ProdutoDAO();
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {

		if (produto == null || produto.getId() == null) {
			throw new BOException("Ocorreu um erro ao buscar o produto pelo ID.");
		}

		return produtoDAO.buscarProdutoPorId(produto);
	}

	
	
	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {

		if (client == null || client.getId() == null) {
			throw new BOException();
		}

		if (status != null && status.trim().length() > 1) {

			if (status.equals("A")) {

				throw new BOValidationException("CPF: erro de validação. " + "O Status informado está incorreto.");
			}
		}

		return produtoDAO.listarProduto(id, descri, status, codbar, client);
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException {

		if (produto == null) {
			throw new BOException("Não é possível salvar o produto pois o objeto é nulo.");
		} else if (produto.getDescri() == null || produto.getDescri().trim().length() == 0) {
			throw new BOValidationException("Nome: erro de validação. " + "O nome deve ser preenchido.");

		} else if (produto.getCodbar() == null) {
			throw new BOValidationException(
					"Código de barras: erro de validação. " + "O Codigo de barras deve ser preenchido.");

		} else if (produto.getStatus() == null) {
			throw new BOException("Ocorreu um erro ao salvar o campo status." + " O valor deveria ter sido informado.");

		} 
			produtoDAO.salvarProduto(produto);
		
	}

	@Override
	public void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException {
		if (produto == null || produto.getId() == null) {
			throw new BOException("Ocorreu um erro ao excluir o produto.");
		}

		produtoDAO.excluirProduto(produto);

	}

	@Override
	public List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
