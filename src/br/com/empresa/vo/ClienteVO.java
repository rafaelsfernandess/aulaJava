package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class ClienteVO implements Serializable {

	private static final long serialVersionUID = 1112726238207419475L;


	private BigInteger id;
	
	//Nome do cliente - 100 caractere
	private String descri;

	public ClienteVO() {
		super();
	}

	public ClienteVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
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
		ClienteVO other = (ClienteVO) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return descri;
	}
	

}













