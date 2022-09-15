package br.com.empresa.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public interface IServicoBeanLocal {
	
	public abstract UsuarioVO validarAcesso(String login, String senha)
			throws BOValidationException, BOException ;
	
	public abstract List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuario) 
			throws BOException;

	public abstract int buscarQuantidadeClientesUsuario(UsuarioVO usuario) 
			throws BOException;
	
	//		-_-_-_-_-_-_-_-_-_ PESSOAS -_-_-_-_-_-_-_-_-_
	
	public abstract List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa,
		String cpfCnpj, String cidade, String estado, ClienteVO cliente)
		throws BOValidationException, BOException;
	
	public abstract void salvarPessoa(PessoaVO pessoa) throws BOValidationException,
		BOException;
	
	public abstract void excluirPessoa(PessoaVO pessoa) throws BOValidationException,
		BOException;
	
	public abstract PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException;
	
	public abstract List<PessoaVO> listarPessoas(int first, int pageSize, 
			Map<String, Object> filters, ClienteVO cliente) throws BOException;
	
	
	//	-_-_-_-_-_-_-_-_-_ PRODUTOS-_-_-_-_-_-_-_-_-_-_-_-_
	
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException;

	public abstract List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar,ClienteVO client) throws BOException;
	
	public abstract int listarProdutoCount(BigInteger id, String descri, String status, String codbar,
			ClienteVO client) throws BOException;

	public abstract void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException;

	public abstract void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException;

	public abstract List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters,
			ClienteVO cliente) throws BOException;

}














