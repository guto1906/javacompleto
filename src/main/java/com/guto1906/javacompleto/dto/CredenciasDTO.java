package com.guto1906.javacompleto.dto;

import java.io.Serializable;

import com.guto1906.javacompleto.services.validation.ClienteUpdate;

@ClienteUpdate
public class CredenciasDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	
	private String senha;
	
	public CredenciasDTO() {		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
