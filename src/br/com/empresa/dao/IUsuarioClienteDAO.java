package br.com.empresa.dao;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioClienteDAO {
	
	public abstract List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuario)
		throws BOException;
	
	public abstract int buscarQuantidadeClientesUsuario(UsuarioVO usuario)
		throws BOException;

}
