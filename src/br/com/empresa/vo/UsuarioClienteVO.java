package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Esta classe é responsável por manter a ligação entre o usuário e o cliente.
 * 
 * @since 11/08/2022
 * @author rogerio.cortina
 * 
 * Revisão
 * 11/08/2022 - Foi criada classe - Chamado 15454
 *
 */
public class UsuarioClienteVO implements Serializable {
	
	private static final long serialVersionUID = -6579470528248612010L;

	private BigInteger id;
	
	private UsuarioVO usuarioVO;
	
	private ClienteVO clienteVO;

	public UsuarioClienteVO() {
		super();
	}

	public UsuarioClienteVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioClienteVO other = (UsuarioClienteVO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
