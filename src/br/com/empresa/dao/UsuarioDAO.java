package br.com.empresa.dao;

import java.util.List;

import br.com.empresa.vo.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO {

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) {
		
		List<UsuarioVO> usuarioVOs = Dados.getUsuarioVOs();
			
		for (UsuarioVO usuarioVO : usuarioVOs) {
			if(usuarioVO.getLogusu().equals(login) && usuarioVO.getSenusu().equals(senha)) {
				return usuarioVO;
			}
		}
		
		return null;
	}

}
