package br.com.empresa.bo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public interface IProdutoBO {
	
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException;

	public abstract List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException;

	public abstract int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException;

	public abstract void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException;

	public abstract void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException;

	public abstract List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters,
			ClienteVO cliente) throws BOException;
	
}
